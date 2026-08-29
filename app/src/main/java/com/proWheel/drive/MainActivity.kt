package com.proWheel.drive

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.proWheel.drive.data.AppDatabase
import com.proWheel.drive.data.AppUser
import com.proWheel.drive.data.Student
import com.proWheel.drive.ui.screens.DashboardScreen
import com.proWheel.drive.ui.screens.LoginScreen
import com.proWheel.drive.ui.screens.NavigationItem
import com.proWheel.drive.ui.screens.RegisterScreen
import com.proWheel.drive.ui.screens.SimplePage
import com.proWheel.drive.ui.screens.StudentDetailsScreen
import com.proWheel.drive.ui.screens.StudentsScreen
import com.proWheel.drive.ui.theme.FingerPrint3Theme
import com.proWheel.drive.utils.passwordUtils.PasswordUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : FragmentActivity() {

    private val preferencesName =
        "proWheel_preferences"


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

                            username =
                                loggedInUsername,

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
                    withContext(
                        Dispatchers.IO
                    ) {

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
                            preferencesName,MODE_PRIVATE
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
                    withContext(
                        Dispatchers.IO
                    ) {

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
                        username =
                            username,

                        passwordHash =
                            passwordHash,

                        mobile =
                            mobile
                    )


                withContext(
                    Dispatchers.IO
                ) {

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
    // LOGIN SCREEN
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
                preferencesName,MODE_PRIVATE
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



// =============================================================
// REGISTER SCREEN
// =============================================================




// =============================================================
// MAIN APPLICATION SCREEN
// =============================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplicationScreen(

    username: String,

    onLogout:
        () -> Unit
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

        mutableStateOf(
            "Dashboard"
        )
    }


    var selectedStudent by remember {

        mutableStateOf<com.proWheel.drive.data.Student?>(
            null
        )
    }


    var students by remember {

        mutableStateOf<List<com.proWheel.drive.data.Student>>(
            emptyList()
        )
    }


    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    LaunchedEffect(
        selectedPage
    ) {

        if (
            selectedPage == "Students" ||
            selectedPage == "Dashboard"
        ) {

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
                    "Unable to load students",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    // =========================================================
    // DRAWER
    // =========================================================

    ModalNavigationDrawer(

        drawerState =
            drawerState,

        drawerContent = {

            ModalDrawerSheet {

                Spacer(
                    modifier =
                        Modifier.height(30.dp)
                )


                Column(

                    modifier =
                        Modifier.padding(
                            horizontal = 20.dp
                        )
                ) {

                    Text(
                        text =
                            "PRO WHEELS",

                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall,

                        fontWeight =
                            FontWeight.Bold
                    )


                    Text(
                        text =
                            "Motor Driving School",

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )


                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )


                    Surface(

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            MaterialTheme
                                .shapes
                                .medium,

                        color =
                            MaterialTheme
                                .colorScheme
                                .surfaceVariant
                    ) {

                        Column(

                            modifier =
                                Modifier.padding(
                                    14.dp
                                )
                        ) {

                            Text(
                                text =
                                    "Logged in as",

                                style =
                                    MaterialTheme
                                        .typography
                                        .labelMedium
                            )


                            Text(
                                text =
                                    username,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                NavigationItem(

                    text =
                        "Dashboard",

                    selected =
                        selectedPage ==
                                "Dashboard"
                ) {

                    selectedPage =
                        "Dashboard"

                    selectedStudent =
                        null

                    scope.launch {
                        drawerState.close()
                    }
                }


                NavigationItem(

                    text =
                        "Students",

                    selected =
                        selectedPage ==
                                "Students"
                ) {

                    selectedPage =
                        "Students"

                    selectedStudent =
                        null

                    scope.launch {
                        drawerState.close()
                    }
                }


                NavigationItem(

                    text =
                        "Attendance",

                    selected =
                        selectedPage ==
                                "Attendance"
                ) {

                    selectedPage =
                        "Attendance"

                    selectedStudent =
                        null

                    scope.launch {
                        drawerState.close()
                    }
                }


                NavigationItem(

                    text =
                        "Settings",

                    selected =
                        selectedPage ==
                                "Settings"
                ) {

                    selectedPage =
                        "Settings"

                    selectedStudent =
                        null

                    scope.launch {
                        drawerState.close()
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                NavigationItem(

                    text =
                        "Logout",

                    selected =
                        false
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

                            when {

                                selectedPage ==
                                        "Student Registration" ->

                                    "Add Student"


                                selectedPage ==
                                        "Student Details" ->

                                    "Student Details"


                                else ->

                                    selectedPage
                            }
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
                                    "Open menu"
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

                        username =
                            username,

                        studentCount =
                            students.size,

                        modifier =
                            Modifier.padding(
                                paddingValues
                            ),

                        onOpenStudents = {

                            selectedPage =
                                "Students"
                        }
                    )
                }


                // =================================================
                // STUDENTS
                // =================================================

                "Students" -> {

                    StudentsScreen(

                        students =
                            students,

                        modifier =
                            Modifier.padding(
                                paddingValues
                            ),

                        onAddStudent = {

                            selectedPage =
                                "Student Registration"
                        },

                        onStudentClick = { student: Student ->

                            selectedStudent =
                                student

                            selectedPage =
                                "Student Details"
                        },


                        onDeleteStudent = { student: Student ->

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

                                        // FIX:
                                        // DAO expects Student,
                                        // not Int.
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

                                } catch (
                                    e: Exception
                                ) {

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
                // REGISTRATION
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

                                } catch (
                                    e: Exception
                                ) {

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

                            student =
                                student,

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
                            "Attendance management will be added here.",

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
                            "Application settings will be added here.",

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
// NAVIGATION ITEM
// =============================================================




// =============================================================
// DASHBOARD
// =============================================================




// =============================================================
// STUDENTS SCREEN
// =============================================================




// =============================================================
// STUDENT CARD
// =============================================================




// =============================================================
// STUDENT INFO ROW
// =============================================================




// =============================================================
// SIMPLE PAGE
// =============================================================