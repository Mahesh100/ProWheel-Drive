package com.proWheel.drive

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.proWheel.drive.data.AppDatabase
import com.proWheel.drive.data.Student
import com.proWheel.drive.ui.theme.FingerPrint3Theme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FingerPrint3Theme {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ProWheelDriveApp()
                    }
                }
            }
        }
    }

    // =========================================================
    // SAVE STUDENT
    // =========================================================

    private fun saveStudent(student: Student) {

        val database = AppDatabase.getDatabase(this)

        lifecycleScope.launch {

            try {

                val studentId = database
                    .studentDao()
                    .insertStudent(student)

                Toast.makeText(
                    this@MainActivity,
                    "Student saved successfully. ID: $studentId",
                    Toast.LENGTH_LONG
                ).show()

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Error saving student: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // =========================================================
    // GET ALL STUDENTS
    // =========================================================

    private fun getStudents(
        onResult: (List<Student>) -> Unit
    ) {

        val database = AppDatabase.getDatabase(this)

        lifecycleScope.launch {

            try {

                val students = database
                    .studentDao()
                    .getAllStudents()

                onResult(students)

            } catch (e: Exception) {

                Toast.makeText(
                    this@MainActivity,
                    "Error loading students: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // =========================================================
    // MAIN APP
    // =========================================================

    @Composable
    private fun ProWheelDriveApp() {

        var showStudents by remember {
            mutableStateOf(false)
        }

        if (showStudents) {

            RegisteredStudentsScreen(
                onBack = {
                    showStudents = false
                },
                loadStudents = {
                    getStudents(it)
                }
            )

        } else {

            StudentRegistrationScreen(
                onSaveStudent = { student ->
                    saveStudent(student)
                },
                onViewStudents = {
                    showStudents = true
                }
            )
        }
    }
}

// =============================================================
// STUDENT REGISTRATION SCREEN
// =============================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentRegistrationScreen(
    onSaveStudent: (Student) -> Unit,
    onViewStudents: () -> Unit
) {

    var admissionDate by remember { mutableStateOf("") }
    var timing by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var services by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var totalFees by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var courseExpanded by remember { mutableStateOf(false) }

    val courseOptions = listOf(
        "4W Training",
        "2W Training",
        "2W + 4W Training"
    )

    var servicesExpanded by remember { mutableStateOf(false) }

    val serviceOptions = listOf(
        "New Learner License",
        "LMV License",
        "LMV Endorsement",
        "Renewal",
        "Other"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text(
            text = "PRO WHEEL DRIVE",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Motor Driving School",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Student Registration",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // -----------------------------------------------------
        // ADMISSION DATE
        // -----------------------------------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDatePicker = true
                }
        ) {

            OutlinedTextField(
                value = admissionDate,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = {
                    Text("Admission Date")
                },
                placeholder = {
                    Text("Select Date")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // TRAINING TIMING
        // -----------------------------------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showTimePicker = true
                }
        ) {

            OutlinedTextField(
                value = timing,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = {
                    Text("Training Timing")
                },
                placeholder = {
                    Text("Select Time")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // NAME
        // -----------------------------------------------------

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Student Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // COURSE
        // -----------------------------------------------------

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = course,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = {
                    Text("Course")
                },
                placeholder = {
                    Text("Select Course")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        courseExpanded = true
                    }
            )

            DropdownMenu(
                expanded = courseExpanded,
                onDismissRequest = {
                    courseExpanded = false
                }
            ) {

                courseOptions.forEach { option ->

                    DropdownMenuItem(
                        text = {
                            Text(option)
                        },
                        onClick = {

                            course = option
                            courseExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // SERVICES
        // -----------------------------------------------------

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = services,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = {
                    Text("Services")
                },
                placeholder = {
                    Text("Select Service")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        servicesExpanded = true
                    }
            )

            DropdownMenu(
                expanded = servicesExpanded,
                onDismissRequest = {
                    servicesExpanded = false
                }
            ) {

                serviceOptions.forEach { option ->

                    DropdownMenuItem(
                        text = {
                            Text(option)
                        },
                        onClick = {

                            services = option
                            servicesExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // ADDRESS
        // -----------------------------------------------------

        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it
            },
            label = {
                Text("Address")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // MOBILE
        // -----------------------------------------------------

        OutlinedTextField(
            value = mobile,
            onValueChange = {
                mobile = it
            },
            label = {
                Text("Mobile Number")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // TOTAL FEES
        // -----------------------------------------------------

        OutlinedTextField(
            value = totalFees,
            onValueChange = {
                totalFees = it
            },
            label = {
                Text("Total Fees")
            },
            placeholder = {
                Text("Example: ₹7000")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // -----------------------------------------------------
        // SAVE
        // -----------------------------------------------------

        Button(
            onClick = {

                val student = Student(
                    admissionDate = admissionDate,
                    trainingTime = timing,
                    name = name,
                    course = course,
                    services = services,
                    address = address,
                    mobile = mobile,
                    totalFees = totalFees
                )

                onSaveStudent(student)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SAVE STUDENT")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // VIEW STUDENTS
        // -----------------------------------------------------

        Button(
            onClick = onViewStudents,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("VIEW REGISTERED STUDENTS")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // -----------------------------------------------------
        // FINGERPRINT
        // -----------------------------------------------------

        Button(
            onClick = {
                // MSO 1300 E3 integration will be added later
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ENROLL FINGERPRINT")
        }
    }

    // =========================================================
    // DATE PICKER
    // =========================================================

    if (showDatePicker) {

        val datePickerState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        datePickerState.selectedDateMillis?.let { millis ->

                            val formatter = SimpleDateFormat(
                                "dd/MM/yyyy",
                                Locale.getDefault()
                            )

                            admissionDate = formatter.format(
                                Date(millis)
                            )
                        }

                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {

                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {

            DatePicker(
                state = datePickerState
            )
        }
    }

    // =========================================================
    // TIME PICKER
    // =========================================================

    if (showTimePicker) {

        val timePickerState = rememberTimePickerState(
            initialHour = 10,
            initialMinute = 0,
            is24Hour = false
        )

        AlertDialog(
            onDismissRequest = {
                showTimePicker = false
            },
            title = {
                Text("Select Training Time")
            },
            text = {
                TimePicker(
                    state = timePickerState
                )
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        val hour = timePickerState.hour
                        val minute = timePickerState.minute

                        val amPm = if (hour < 12) {
                            "AM"
                        } else {
                            "PM"
                        }

                        val displayHour = when {
                            hour == 0 -> 12
                            hour > 12 -> hour - 12
                            else -> hour
                        }

                        timing = String.format(
                            Locale.getDefault(),
                            "%02d:%02d %s",
                            displayHour,
                            minute,
                            amPm
                        )

                        showTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {

                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

// =============================================================
// REGISTERED STUDENTS SCREEN
// =============================================================

@Composable
fun RegisteredStudentsScreen(
    onBack: () -> Unit,
    loadStudents: ((List<Student>) -> Unit) -> Unit
) {

    var students by remember {
        mutableStateOf<List<Student>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(true)
    }

    fun refreshStudents() {

        loading = true

        loadStudents { result ->

            students = result
            loading = false
        }
    }

    LaunchedEffect(Unit) {
        refreshStudents()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // =====================================================
        // HEADER
        // =====================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TextButton(
                onClick = onBack
            ) {
                Text("← Back")
            }

            Text(
                text = "Registered Students",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // =====================================================
        // REFRESH
        // =====================================================

        Button(
            onClick = {
                refreshStudents()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("REFRESH")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // =====================================================
        // STUDENT LIST
        // =====================================================

        if (loading) {

            Text(
                text = "Loading students..."
            )

        } else if (students.isEmpty()) {

            Text(
                text = "No students registered yet."
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                items(
                    items = students,
                    key = {
                        it.id
                    }
                ) { student ->

                    StudentCard(student)

                    Spacer(
                        modifier = Modifier.height(10.dp)
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
fun StudentCard(student: Student) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = student.name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Student ID: ${student.id}"
            )

            Text(
                text = "Admission Date: ${student.admissionDate}"
            )

            Text(
                text = "Training Time: ${student.trainingTime}"
            )

            Text(
                text = "Course: ${student.course}"
            )

            Text(
                text = "Service: ${student.services}"
            )

            Text(
                text = "Mobile: ${student.mobile}"
            )

            Text(
                text = "Fees: ${student.totalFees}"
            )
        }
    }
}