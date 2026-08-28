package com.proWheel.drive

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.proWheel.drive.data.AppDatabase
import com.proWheel.drive.data.AppUser
import com.proWheel.drive.data.Student
import com.proWheel.drive.ui.theme.FingerPrint3Theme
import com.proWheel.drive.utils.passwordUtils.PasswordUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : FragmentActivity() {

    private val preferencesName =
        "prowheel_preferences"

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val preferences =
            getSharedPreferences(
                preferencesName,
                Context.MODE_PRIVATE
            )

        val isLoggedIn =
            preferences.getBoolean(
                "is_logged_in",
                false
            )

        val loggedInUsername =
            preferences.getString(
                "username",
                ""
            ) ?: ""

        setContent {

            FingerPrint3Theme {

                MaterialTheme {

                    if (isLoggedIn) {

                        MainApplicationScreen(
                            username = loggedInUsername,

                            onLogout = {
                                logout()
                            }
                        )

                    } else {

                        LoginScreen(

                            onLogin = {
                                    username,
                                    password,
                                    keepLoggedIn ->

                                loginUser(
                                    username,
                                    password,
                                    keepLoggedIn
                                )
                            },

                            onRegister = {
                                showRegisterScreen()
                            }
                        )
                    }
                }
            }
        }
    }


    // =========================================================
    // LOGIN
    // =========================================================

    private fun loginUser(
        username: String,
        password: String,
        keepLoggedIn: Boolean
    ) {

        if (
            username.isBlank() ||
            password.isBlank()
        ) {

            Toast.makeText(
                this,
                "Please enter username and password",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        lifecycleScope.launch {

            try {

                val database =
                    AppDatabase.getDatabase(
                        this@MainActivity
                    )

                val passwordHash =
                    PasswordUtils.hashPassword(
                        password
                    )

                val user =
                    withContext(Dispatchers.IO) {

                        database
                            .userDao()
                            .login(
                                username,
                                passwordHash
                            )
                    }

                if (user != null) {

                    val preferences =
                        getSharedPreferences(
                            preferencesName,
                            Context.MODE_PRIVATE
                        )

                    preferences.edit()
                        .putBoolean(
                            "is_logged_in",
                            keepLoggedIn
                        )
                        .putString(
                            "username",
                            user.username
                        )
                        .apply()

                    setContent {

                        FingerPrint3Theme {

                            MaterialTheme {

                                MainApplicationScreen(
                                    username =
                                        user.username,

                                    onLogout = {
                                        logout()
                                    }
                                )
                            }
                        }
                    }

                    Toast.makeText(
                        this@MainActivity,
                        "Login successful",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    Toast.makeText(
                        this@MainActivity,
                        "Invalid username or password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Login error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    // =========================================================
    // REGISTER SCREEN
    // =========================================================

    private fun showRegisterScreen() {

        setContent {

            FingerPrint3Theme {

                MaterialTheme {

                    RegisterScreen(

                        onRegister = {
                                username,
                                password,
                                confirmPassword,
                                mobile ->

                            registerUser(
                                username,
                                password,
                                confirmPassword,
                                mobile
                            )
                        },

                        onBackToLogin = {
                            showLoginScreen()
                        }
                    )
                }
            }
        }
    }


    // =========================================================
    // REGISTER USER
    // =========================================================

    private fun registerUser(
        username: String,
        password: String,
        confirmPassword: String,
        mobile: String
    ) {

        if (username.isBlank()) {

            Toast.makeText(
                this,
                "Please enter username",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (password.isBlank()) {

            Toast.makeText(
                this,
                "Please enter password",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (password != confirmPassword) {

            Toast.makeText(
                this,
                "Passwords do not match",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        lifecycleScope.launch {

            try {

                val database =
                    AppDatabase.getDatabase(
                        this@MainActivity
                    )

                val existingUser =
                    withContext(Dispatchers.IO) {

                        database
                            .userDao()
                            .getUserByUsername(
                                username
                            )
                    }

                if (existingUser != null) {

                    Toast.makeText(
                        this@MainActivity,
                        "Username already exists",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@launch
                }

                val passwordHash =
                    PasswordUtils.hashPassword(
                        password
                    )

                val user =
                    AppUser(
                        username = username,
                        passwordHash = passwordHash,
                        mobile = mobile
                    )

                withContext(Dispatchers.IO) {

                    database
                        .userDao()
                        .insertUser(user)
                }

                Toast.makeText(
                    this@MainActivity,
                    "Registration successful. Please login.",
                    Toast.LENGTH_LONG
                ).show()

                showLoginScreen()

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Registration error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    // =========================================================
    // SHOW LOGIN
    // =========================================================

    private fun showLoginScreen() {

        setContent {

            FingerPrint3Theme {

                MaterialTheme {

                    LoginScreen(

                        onLogin = {
                                username,
                                password,
                                keepLoggedIn ->

                            loginUser(
                                username,
                                password,
                                keepLoggedIn
                            )
                        },

                        onRegister = {
                            showRegisterScreen()
                        }
                    )
                }
            }
        }
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private fun logout() {

        val preferences =
            getSharedPreferences(
                preferencesName,
                Context.MODE_PRIVATE
            )

        preferences.edit()
            .clear()
            .apply()

        showLoginScreen()

        Toast.makeText(
            this,
            "Logged out",
            Toast.LENGTH_SHORT
        ).show()
    }
}


// =============================================================
// LOGIN SCREEN
// =============================================================

@Composable
fun LoginScreen(
    onLogin:
        (String, String, Boolean) -> Unit,

    onRegister: () -> Unit
) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var keepLoggedIn by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "PRO WHEEL DRIVE",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Spacer(
            modifier =
                Modifier.height(4.dp)
        )

        Text(
            text = "Motor Driving School",

            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )

        Spacer(
            modifier =
                Modifier.height(32.dp)
        )

        Text(
            text = "Login",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = username,

            onValueChange = {
                username = it
            },

            label = {
                Text("Username")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Row(
            modifier =
                Modifier.fillMaxWidth()
        ) {

            Checkbox(
                checked = keepLoggedIn,

                onCheckedChange = {
                    keepLoggedIn = it
                }
            )

            Text(
                text = "Keep me logged in",

                modifier =
                    Modifier.padding(
                        top = 12.dp
                    )
            )
        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        Button(

            onClick = {

                onLogin(
                    username,
                    password,
                    keepLoggedIn
                )
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("LOGIN")
        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        TextButton(

            onClick = onRegister,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                "New user? REGISTER"
            )
        }
    }
}


// =============================================================
// REGISTER SCREEN
// =============================================================

@Composable
fun RegisterScreen(
    onRegister:
        (String, String, String, String) -> Unit,

    onBackToLogin: () -> Unit
) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var mobile by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "PRO WHEEL DRIVE",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Spacer(
            modifier =
                Modifier.height(4.dp)
        )

        Text(
            text = "Create New Account",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = username,

            onValueChange = {
                username = it
            },

            label = {
                Text("Username")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = confirmPassword,

            onValueChange = {
                confirmPassword = it
            },

            label = {
                Text("Confirm Password")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = mobile,

            onValueChange = {
                mobile = it
            },

            label = {
                Text("Mobile Number")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        Button(

            onClick = {

                onRegister(
                    username,
                    password,
                    confirmPassword,
                    mobile
                )
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("REGISTER")
        }

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        TextButton(

            onClick =
                onBackToLogin,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                "Already have an account? LOGIN"
            )
        }
    }
}


// =============================================================
// MAIN APPLICATION SCREEN
// =============================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplicationScreen(
    username: String,
    onLogout: () -> Unit
) {

    val context =
        LocalContext.current

    val drawerState =
        rememberDrawerState(
            initialValue =
                DrawerValue.Closed
        )

    val scope =
        rememberCoroutineScope()

    var selectedPage by remember {
        mutableStateOf("Dashboard")
    }

    var students by remember {
        mutableStateOf<List<Student>>(
            emptyList()
        )
    }

    // NEW:
    // Stores the student selected from the list.
    var selectedStudent by remember {
        mutableStateOf<Student?>(null)
    }


    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    LaunchedEffect(selectedPage) {

        if (selectedPage == "Students") {

            try {

                val database =
                    AppDatabase.getDatabase(
                        context
                    )

                students =
                    withContext(
                        Dispatchers.IO
                    ) {

                        database
                            .studentDao()
                            .getAllStudents()
                    }

            } catch (e: Exception) {

                Toast.makeText(
                    context,
                    "Unable to load students: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    // =========================================================
    // NAVIGATION DRAWER
    // =========================================================

    ModalNavigationDrawer(

        drawerState =
            drawerState,

        drawerContent = {

            ModalDrawerSheet {

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Text(
                    text =
                        "PRO WHEEL DRIVE",

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,

                    modifier =
                        Modifier.padding(20.dp)
                )

                Text(
                    text =
                        "User: $username",

                    modifier =
                        Modifier.padding(
                            horizontal = 20.dp
                        )
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                DrawerItem(
                    text = "Dashboard",

                    selected =
                        selectedPage ==
                                "Dashboard"
                ) {

                    selectedPage =
                        "Dashboard"

                    selectedStudent = null

                    scope.launch {
                        drawerState.close()
                    }
                }


                DrawerItem(
                    text = "Students",

                    selected =
                        selectedPage ==
                                "Students"
                ) {

                    selectedPage =
                        "Students"

                    selectedStudent = null

                    scope.launch {
                        drawerState.close()
                    }
                }


                DrawerItem(
                    text = "Attendance",

                    selected =
                        selectedPage ==
                                "Attendance"
                ) {

                    selectedPage =
                        "Attendance"

                    selectedStudent = null

                    scope.launch {
                        drawerState.close()
                    }
                }


                DrawerItem(
                    text = "Settings",

                    selected =
                        selectedPage ==
                                "Settings"
                ) {

                    selectedPage =
                        "Settings"

                    selectedStudent = null

                    scope.launch {
                        drawerState.close()
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                DrawerItem(
                    text = "Logout",

                    selected = false
                ) {

                    scope.launch {
                        drawerState.close()
                    }

                    onLogout()
                }
            }
        }
    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {

                        Text(
                            selectedPage
                        )
                    },

                    navigationIcon = {

                        IconButton(

                            onClick = {

                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.Menu,

                                contentDescription =
                                    "Menu"
                            )
                        }
                    }
                )
            }

        ) { paddingValues ->


            when (selectedPage) {


                // =================================================
                // DASHBOARD
                // =================================================

                "Dashboard" -> {

                    DashboardScreen(

                        username = username,

                        modifier =
                            Modifier.padding(
                                paddingValues
                            )
                    )
                }


                // =================================================
                // STUDENTS
                // =================================================

                "Students" -> {

                    StudentsScreen(

                        students = students,

                        modifier =
                            Modifier.padding(
                                paddingValues
                            ),

                        onAddStudent = {

                            selectedPage =
                                "Student Registration"
                        },

                        // NEW:
                        // Student card click.
                        onStudentClick = { student ->

                            selectedStudent =
                                student

                            selectedPage =
                                "Student Details"
                        },

                        onDeleteStudent = {
                                student ->

                            scope.launch {

                                try {

                                    val database =
                                        AppDatabase
                                            .getDatabase(
                                                context
                                            )

                                    withContext(
                                        Dispatchers.IO
                                    ) {

                                        database
                                            .studentDao()
                                            .deleteStudent(
                                                student.id
                                            )
                                    }

                                    students =
                                        withContext(
                                            Dispatchers.IO
                                        ) {

                                            database
                                                .studentDao()
                                                .getAllStudents()
                                        }

                                    Toast.makeText(
                                        context,
                                        "Student deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } catch (e: Exception) {

                                    Toast.makeText(
                                        context,
                                        "Delete failed: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    )
                }


                // =================================================
                // STUDENT REGISTRATION
                // =================================================

                "Student Registration" -> {

                    StudentRegistrationScreen(

                        onBack = {

                            selectedPage =
                                "Students"
                        },

                        onStudentSaved = {
                                student ->

                            scope.launch {

                                try {

                                    val database =
                                        AppDatabase
                                            .getDatabase(
                                                context
                                            )

                                    withContext(
                                        Dispatchers.IO
                                    ) {

                                        database
                                            .studentDao()
                                            .insertStudent(
                                                student
                                            )
                                    }

                                    students =
                                        withContext(
                                            Dispatchers.IO
                                        ) {

                                            database
                                                .studentDao()
                                                .getAllStudents()
                                        }

                                    Toast.makeText(
                                        context,
                                        "Student saved successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    selectedPage =
                                        "Students"

                                } catch (e: Exception) {

                                    Toast.makeText(
                                        context,
                                        "Unable to save student: ${e.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    )
                }


                // =================================================
                // STUDENT DETAILS
                // =================================================

                "Student Details" -> {

                    selectedStudent?.let {
                            student ->

                        StudentDetailsScreen(

                            student = student,

                            onBack = {

                                selectedStudent =
                                    null

                                selectedPage =
                                    "Students"
                            },

                            onEnrollFingerprint = {

                                Toast.makeText(
                                    context,
                                    "Fingerprint enrollment will be implemented next",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }


                // =================================================
                // ATTENDANCE
                // =================================================

                "Attendance" -> {

                    SimplePage(

                        title =
                            "Attendance",

                        message =
                            "Attendance management will be here.",

                        modifier =
                            Modifier.padding(
                                paddingValues
                            )
                    )
                }


                // =================================================
                // SETTINGS
                // =================================================

                "Settings" -> {

                    SimplePage(

                        title =
                            "Settings",

                        message =
                            "Application settings will be here.",

                        modifier =
                            Modifier.padding(
                                paddingValues
                            )
                    )
                }
            }
        }
    }
}


// =============================================================
// DRAWER ITEM
// =============================================================

@Composable
fun DrawerItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Text(
        text = text,

        style =
            MaterialTheme
                .typography
                .titleMedium,

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 24.dp,
                vertical = 14.dp
            )
    )
}


// =============================================================
// DASHBOARD SCREEN
// =============================================================

@Composable
fun DashboardScreen(
    username: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text =
                "Welcome, $username",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Card(
            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(
                    text =
                        "Registered Students",

                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(
                    text =
                        "Open Students to view records.",

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(16.dp)
        )


        Card(
            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(
                    text =
                        "Today's Attendance",

                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(
                    text =
                        "Attendance management will be added next."
                )
            }
        }
    }
}


// =============================================================
// STUDENTS SCREEN
// =============================================================

@Composable
fun StudentsScreen(
    students: List<Student>,

    modifier: Modifier = Modifier,

    onAddStudent: () -> Unit,

    onStudentClick:
        (Student) -> Unit,

    onDeleteStudent:
        (Student) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text =
                "Registered Students",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // ADD STUDENT
        // =====================================================

        Button(

            onClick =
                onAddStudent,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("+ ADD STUDENT")
        }

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // EMPTY STATE
        // =====================================================

        if (students.isEmpty()) {

            Text(
                text =
                    "No students registered yet."
            )

        } else {


            // =================================================
            // STUDENT LIST
            // =================================================

            LazyColumn(

                modifier =
                    Modifier.fillMaxSize(),

                verticalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {

                items(

                    items = students,

                    key = {
                        it.id
                    }

                ) { student ->

                    StudentCard(

                        student =
                            student,

                        onClick = {

                            onStudentClick(
                                student
                            )
                        },

                        onDelete = {

                            onDeleteStudent(
                                student
                            )
                        }
                    )
                }
            }
        }
    }
}


// =============================================================
// STUDENT CARD
// =============================================================

@Composable
fun StudentCard(
    student: Student,

    onClick: () -> Unit,

    onDelete: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                onClick()
            }
    ) {

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Text(
                text =
                    student.name,

                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    "Mobile: ${student.mobile}"
            )

            Text(
                text =
                    "Course: ${student.course}"
            )

            Text(
                text =
                    "Service: ${student.services}"
            )

            Text(
                text =
                    "Admission: ${student.admissionDate}"
            )

            Text(
                text =
                    "Training: ${student.trainingTime}"
            )

            Text(
                text =
                    "Fees: ${student.totalFees}"
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            TextButton(
                onClick =
                    onDelete
            ) {

                Text("DELETE")
            }
        }
    }
}


// =============================================================
// SIMPLE PAGE
// =============================================================

@Composable
fun SimplePage(
    title: String,

    message: String,

    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = title,

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Spacer(
            modifier =
                Modifier.height(16.dp)
        )

        Text(
            text = message
        )
    }
}