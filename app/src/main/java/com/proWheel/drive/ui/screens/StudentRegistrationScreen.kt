package com.proWheel.drive.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    // FORM STATE
    // =========================================================

    var admissionDate by remember {
        mutableStateOf("")
    }

    var trainingTime by remember {
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
    // VALIDATION STATE
    // =========================================================

    var nameError by remember {
        mutableStateOf(false)
    }

    var mobileError by remember {
        mutableStateOf(false)
    }

    var courseError by remember {
        mutableStateOf(false)
    }

    var dateError by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // PICKER STATE
    // =========================================================

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var showTimePicker by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // DROPDOWN STATE
    // =========================================================

    var courseExpanded by remember {
        mutableStateOf(false)
    }

    var servicesExpanded by remember {
        mutableStateOf(false)
    }


    val courseOptions =
        remember {

            listOf(
                "4W Training",
                "2W Training",
                "2W + 4W Training"
            )
        }


    val serviceOptions =
        remember {

            listOf(
                "New Learner License",
                "LMV License",
                "LMV Endorsement",
                "Renewal",
                "Other"
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

                                admissionDate =
                                    SimpleDateFormat(
                                        "dd MMM yyyy",
                                        Locale.getDefault()
                                    ).format(
                                        Date(millis)
                                    )

                                dateError =
                                    false
                            }

                        showDatePicker = false
                    }
                ) {

                    Text(
                        text = "Select"
                    )
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {
                        showDatePicker = false
                    }
                ) {

                    Text(
                        text = "Cancel"
                    )
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
                is24Hour = false
            )


        TimePickerDialog(

            onDismissRequest = {
                showTimePicker = false
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        val hour =
                            timePickerState.hour

                        val minute =
                            timePickerState.minute


                        val calendar =
                            java.util.Calendar
                                .getInstance()

                        calendar.set(
                            java.util.Calendar.HOUR_OF_DAY,
                            hour
                        )

                        calendar.set(
                            java.util.Calendar.MINUTE,
                            minute
                        )


                        trainingTime =
                            SimpleDateFormat(
                                "hh:mm a",
                                Locale.getDefault()
                            ).format(
                                calendar.time
                            )


                        showTimePicker =
                            false
                    }
                ) {

                    Text(
                        text = "Select"
                    )
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {
                        showTimePicker = false
                    }
                ) {

                    Text(
                        text = "Cancel"
                    )
                }
            }

        ) {

            TimePicker(
                state = timePickerState
            )
        }
    }


    // =========================================================
    // SCREEN
    // =========================================================

    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .imePadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),

        verticalArrangement =
            Arrangement.spacedBy(
                16.dp
            )
    ) {


        // =====================================================
        // HEADER
        // =====================================================

        Column {

            Text(

                text =
                    "Add Student",

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
                    "Enter the student's details below.",

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,

                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }


        // =====================================================
        // PERSONAL DETAILS
        // =====================================================

        FormSectionCard(
            title = "Personal details"
        ) {

            OutlinedTextField(

                value =
                    name,

                onValueChange = {

                    name = it

                    if (it.isNotBlank()) {
                        nameError = false
                    }
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Full name")
                },

                leadingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.Person,

                        contentDescription =
                            null
                    )
                },

                singleLine = true,

                isError =
                    nameError,

                supportingText = {

                    if (nameError) {

                        Text(
                            text =
                                "Please enter student's name"
                        )
                    }
                },

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Text
                    )
            )


            OutlinedTextField(

                value =
                    mobile,

                onValueChange = { value ->

                    mobile =
                        value
                            .filter {
                                it.isDigit()
                            }
                            .take(10)

                    if (mobile.length == 10) {
                        mobileError = false
                    }
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Mobile number")
                },

                leadingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.Phone,

                        contentDescription =
                            null
                    )
                },

                singleLine = true,

                isError =
                    mobileError,

                supportingText = {

                    if (mobileError) {

                        Text(
                            text =
                                "Enter a valid 10-digit mobile number"
                        )
                    }
                },

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Phone
                    )
            )


            OutlinedTextField(

                value =
                    address,

                onValueChange = {
                    address = it
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Address")
                },

                minLines = 3,

                maxLines = 4,

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Text
                    )
            )
        }


        // =====================================================
        // TRAINING DETAILS
        // =====================================================

        FormSectionCard(
            title = "Training details"
        ) {

            // -------------------------------------------------
            // ADMISSION DATE
            // -------------------------------------------------

            OutlinedTextField(

                value =
                    admissionDate,

                onValueChange = {},

                modifier =
                    Modifier
                        .fillMaxWidth(),

                label = {
                    Text("Admission date")
                },

                leadingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.CalendarToday,

                        contentDescription =
                            null
                    )
                },

                readOnly = true,

                isError =
                    dateError,

                supportingText = {

                    if (dateError) {

                        Text(
                            text =
                                "Please select admission date"
                        )
                    }
                },

                placeholder = {

                    Text(
                        text =
                            "Select date"
                    )
                }
            )


            // Clickable date field

            TextButton(

                onClick = {
                    showDatePicker = true
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        if (admissionDate.isBlank())
                            "Choose admission date"
                        else
                            "Change admission date"
                )
            }


            // -------------------------------------------------
            // TRAINING TIME
            // -------------------------------------------------

            OutlinedTextField(

                value =
                    trainingTime,

                onValueChange = {},

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Training time")
                },

                leadingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.Schedule,

                        contentDescription =
                            null
                    )
                },

                readOnly = true,

                placeholder = {

                    Text(
                        text =
                            "Select training time"
                    )
                }
            )


            TextButton(

                onClick = {
                    showTimePicker = true
                },

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        if (trainingTime.isBlank())
                            "Choose training time"
                        else
                            "Change training time"
                )
            }


            // -------------------------------------------------
            // COURSE
            // -------------------------------------------------

            ExposedDropdownMenuBox(

                expanded =
                    courseExpanded,

                onExpandedChange = {
                    courseExpanded =
                        !courseExpanded
                }
            ) {

                OutlinedTextField(

                    value =
                        course,

                    onValueChange = {},

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .menuAnchor(),

                    readOnly = true,

                    label = {
                        Text("Training course")
                    },

                    placeholder = {

                        Text(
                            text =
                                "Select course"
                        )
                    },

                    isError =
                        courseError,

                    supportingText = {

                        if (courseError) {

                            Text(
                                text =
                                    "Please select a course"
                            )
                        }
                    },

                    trailingIcon = {

                        ExposedDropdownMenuDefaults
                            .TrailingIcon(
                                expanded =
                                    courseExpanded
                            )
                    }
                )


                ExposedDropdownMenu(

                    expanded =
                        courseExpanded,

                    onDismissRequest = {
                        courseExpanded =
                            false
                    }
                ) {

                    courseOptions.forEach { option ->

                        DropdownMenuItem(

                            text = {

                                Text(
                                    text =
                                        option
                                )
                            },

                            onClick = {

                                course =
                                    option

                                courseError =
                                    false

                                courseExpanded =
                                    false
                            }
                        )
                    }
                }
            }


            // -------------------------------------------------
            // SERVICE
            // -------------------------------------------------

            ExposedDropdownMenuBox(

                expanded =
                    servicesExpanded,

                onExpandedChange = {

                    servicesExpanded =
                        !servicesExpanded
                }
            ) {

                OutlinedTextField(

                    value =
                        services,

                    onValueChange = {},

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .menuAnchor(),

                    readOnly = true,

                    label = {
                        Text("Service")
                    },

                    placeholder = {

                        Text(
                            text =
                                "Select service"
                        )
                    },

                    trailingIcon = {

                        ExposedDropdownMenuDefaults
                            .TrailingIcon(
                                expanded =
                                    servicesExpanded
                            )
                    }
                )


                ExposedDropdownMenu(

                    expanded =
                        servicesExpanded,

                    onDismissRequest = {

                        servicesExpanded =
                            false
                    }
                ) {

                    serviceOptions.forEach { option ->

                        DropdownMenuItem(

                            text = {

                                Text(
                                    text =
                                        option
                                )
                            },

                            onClick = {

                                services =
                                    option

                                servicesExpanded =
                                    false
                            }
                        )
                    }
                }
            }
        }


        // =====================================================
        // PAYMENT
        // =====================================================

        FormSectionCard(
            title = "Payment"
        ) {

            OutlinedTextField(

                value =
                    totalFees,

                onValueChange = { value ->

                    totalFees =
                        value
                            .filter {
                                it.isDigit()
                            }
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Total fees")
                },

                prefix = {
                    Text("₹ ")
                },

                singleLine = true,

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Number
                    )
            )
        }


        // =====================================================
        // ACTIONS
        // =====================================================

        Row(

            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(
                    12.dp
                )
        ) {

            TextButton(

                onClick =
                    onBack,

                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        "Cancel"
                )
            }


            Button(

                onClick = {

                    nameError =
                        name.isBlank()

                    mobileError =
                        mobile.length != 10

                    courseError =
                        course.isBlank()

                    dateError =
                        admissionDate.isBlank()


                    val isValid =
                        !nameError &&
                                !mobileError &&
                                !courseError &&
                                !dateError


                    if (!isValid) {
                        return@Button
                    }


                    val student =
                        Student(

                            admissionDate =
                                admissionDate,

                            trainingTime =
                                trainingTime,

                            name =
                                name.trim(),

                            course =
                                course,

                            services =
                                services,

                            address =
                                address.trim(),

                            mobile =
                                mobile,

                            totalFees =
                                totalFees
                        )


                    onStudentSaved(
                        student
                    )
                },

                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        "Save Student"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(8.dp)
        )
    }
}


// =================================================================
// FORM SECTION CARD
// =================================================================

@Composable
private fun FormSectionCard(
    title: String,
    content: @Composable () -> Unit
) {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme
                        .colorScheme
                        .surfaceContainerLow
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(
                    12.dp
                )
        ) {

            Text(

                text =
                    title,

                style =
                    MaterialTheme
                        .typography
                        .titleMedium,

                fontWeight =
                    FontWeight.SemiBold
            )


            content()
        }
    }
}


// =================================================================
// TIME PICKER DIALOG
// =================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    content: @Composable () -> Unit
) {

    androidx.compose.material3.AlertDialog(

        onDismissRequest =
            onDismissRequest,

        confirmButton =
            confirmButton,

        dismissButton =
            dismissButton,

        text = {

            content()
        }
    )
}