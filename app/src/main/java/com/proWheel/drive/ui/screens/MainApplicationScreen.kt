package com.proWheel.drive.ui.screens

import android.widget.Toast

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
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
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

import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.proWheel.drive.data.Student
import com.proWheel.drive.ui.viewmodel.StudentViewModel

import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplicationScreen(
    username: String,
    studentViewModel: StudentViewModel,
    onLogout: () -> Unit
) {

    val context =
        LocalContext.current


    // =========================================================
    // DRAWER
    // =========================================================

    val drawerState =
        rememberDrawerState(
            initialValue =
                DrawerValue.Closed
        )


    val scope =
        rememberCoroutineScope()


    // =========================================================
    // NAVIGATION STATE
    // =========================================================

    var selectedPage by remember {

        mutableStateOf(
            "Dashboard"
        )
    }


    var selectedStudent by remember {

        mutableStateOf<Student?>(
            null
        )
    }


    // =========================================================
    // STUDENT STATE
    // =========================================================

    val students by
    studentViewModel
        .students
        .collectAsStateWithLifecycle()


    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    LaunchedEffect(Unit) {

        studentViewModel.loadStudents()
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
                        Modifier.height(24.dp)
                )


                // =================================================
                // HEADER
                // =================================================

                Column(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 20.dp
                            )
                ) {

                    Text(

                        text =
                            "PRO WHEEL DRIVE",

                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall,

                        fontWeight =
                            FontWeight.Bold
                    )


                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )


                    Text(

                        text =
                            "Driving School Management",

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium,

                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )


                    Spacer(
                        modifier =
                            Modifier.height(18.dp)
                    )


                    Text(

                        text =
                            "Welcome, $username",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                // =================================================
                // DASHBOARD
                // =================================================

                DrawerNavigationItem(

                    label =
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


                // =================================================
                // STUDENTS
                // =================================================

                DrawerNavigationItem(

                    label =
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


                // =================================================
                // ATTENDANCE
                // =================================================

                DrawerNavigationItem(

                    label =
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


                // =================================================
                // SETTINGS
                // =================================================

                DrawerNavigationItem(

                    label =
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
                        Modifier.height(16.dp)
                )


                // =================================================
                // LOGOUT
                // =================================================

                DrawerNavigationItem(

                    label =
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


        // =========================================================
        // MAIN CONTENT
        // =========================================================

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {

                        Text(

                            text =
                                when (
                                    selectedPage
                                ) {

                                    "Student Registration" ->
                                        "Add Student"

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
                                    "Open navigation menu"
                            )
                        }
                    }
                )
            }

        ) { paddingValues ->


            // =====================================================
            // PAGE ROUTING
            // =====================================================

            when (
                selectedPage
            ) {


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

                        onStudentClick = {
                                student ->

                            selectedStudent =
                                student

                            selectedPage =
                                "Student Details"
                        },

                        onDeleteStudent = {
                                student ->

                            studentViewModel
                                .deleteStudent(

                                    studentId =
                                        student.id,

                                    onSuccess = {

                                        Toast.makeText(

                                            context,

                                            "Student deleted",

                                            Toast.LENGTH_SHORT

                                        ).show()
                                    },

                                    onError = {
                                            message ->

                                        Toast.makeText(

                                            context,

                                            "Delete failed: $message",

                                            Toast.LENGTH_LONG

                                        ).show()
                                    }
                                )
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

                            studentViewModel
                                .addStudent(

                                    student =
                                        student,

                                    onSuccess = {

                                        Toast.makeText(

                                            context,

                                            "Student saved successfully",

                                            Toast.LENGTH_SHORT

                                        ).show()


                                        selectedPage =
                                            "Students"
                                    },

                                    onError = {
                                            message ->

                                        Toast.makeText(

                                            context,

                                            "Unable to save student: $message",

                                            Toast.LENGTH_LONG

                                        ).show()
                                    }
                                )
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
// DRAWER NAVIGATION ITEM
// =============================================================

@Composable
private fun DrawerNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    NavigationDrawerItem(

        label = {

            Text(
                text =
                    label
            )
        },

        selected =
            selected,

        onClick =
            onClick,

        modifier =
            Modifier.padding(
                horizontal = 12.dp,
                vertical = 3.dp
            )
    )
}