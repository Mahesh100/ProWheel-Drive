package com.proWheel.drive

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proWheel.drive.data.Student
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentRegistrationScreen(
    onBack: () -> Unit,
    onStudentSaved: (Student) -> Unit
) {

    // =========================================================
    // FORM VALUES
    // =========================================================

    var admissionDate by remember {
        mutableStateOf("")
    }

    var timing by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var course by remember {
        mutableStateOf("")
    }

    var services by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    var mobile by remember {
        mutableStateOf("")
    }

    var totalFees by remember {
        mutableStateOf("")
    }


    // =========================================================
    // DATE & TIME PICKER
    // =========================================================

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var showTimePicker by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // COURSE DROPDOWN
    // =========================================================

    var courseExpanded by remember {
        mutableStateOf(false)
    }

    val courseOptions = listOf(
        "4W Training",
        "2W Training",
        "2W + 4W Training"
    )


    // =========================================================
    // SERVICES DROPDOWN
    // =========================================================

    var servicesExpanded by remember {
        mutableStateOf(false)
    }

    val serviceOptions = listOf(
        "New Learner License",
        "LMV License",
        "LMV Endorsement",
        "Renewal",
        "Other"
    )


    // =========================================================
    // SCREEN
    // =========================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp),

        verticalArrangement = Arrangement.Top
    ) {

        // =====================================================
        // BACK BUTTON
        // =====================================================

        TextButton(
            onClick = onBack
        ) {
            Text("← Back to Students")
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )


        // =====================================================
        // HEADER
        // =====================================================

        Text(
            text = "PRO WHEEL DRIVE",

            style =
                MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Motor Driving School",

            style =
                MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Student Registration",

            style =
                MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // =====================================================
        // ADMISSION DATE
        // =====================================================

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

                modifier =
                    Modifier.fillMaxWidth()
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // TRAINING TIMING
        // =====================================================

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

                modifier =
                    Modifier.fillMaxWidth()
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // STUDENT NAME
        // =====================================================

        OutlinedTextField(
            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Student Name")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // COURSE
        // =====================================================

        Box(
            modifier =
                Modifier.fillMaxWidth()
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

                modifier = Modifier
                    .fillMaxWidth()
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

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // SERVICES
        // =====================================================

        Box(
            modifier =
                Modifier.fillMaxWidth()
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

                modifier = Modifier
                    .fillMaxWidth()
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

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // ADDRESS
        // =====================================================

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

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // MOBILE NUMBER
        // =====================================================

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
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // TOTAL FEES
        // =====================================================

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

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // =====================================================
        // SAVE STUDENT
        // =====================================================

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

                onStudentSaved(student)
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("SAVE STUDENT")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // =====================================================
        // FINGERPRINT
        // =====================================================

        Button(

            onClick = {

                // Fingerprint machine integration
                // will be implemented later.

            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text("ENROLL FINGERPRINT")
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )
    }


    // =========================================================
    // DATE PICKER
    // =========================================================

    if (showDatePicker) {

        val datePickerState =
            rememberDatePickerState()

        DatePickerDialog(

            onDismissRequest = {
                showDatePicker = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        datePickerState
                            .selectedDateMillis
                            ?.let { millis ->

                                val formatter =
                                    SimpleDateFormat(
                                        "dd/MM/yyyy",
                                        Locale.getDefault()
                                    )

                                admissionDate =
                                    formatter.format(
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

        val timePickerState =
            rememberTimePickerState(

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

                        val hour =
                            timePickerState.hour

                        val minute =
                            timePickerState.minute

                        val amPm =
                            if (hour < 12) {
                                "AM"
                            } else {
                                "PM"
                            }

                        val displayHour =
                            when {

                                hour == 0 ->
                                    12

                                hour > 12 ->
                                    hour - 12

                                else ->
                                    hour
                            }

                        timing =
                            String.format(
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