package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
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

    var dateError by remember {
        mutableStateOf(false)
    }

    var timeError by remember {
        mutableStateOf(false)
    }

    var courseError by remember {
        mutableStateOf(false)
    }

    var serviceError by remember {
        mutableStateOf(false)
    }

    var mobileError by remember {
        mutableStateOf(false)
    }

    var feesError by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // PICKERS
    // =========================================================

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var showTimePicker by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // DROPDOWNS
    // =========================================================

    var courseExpanded by remember {
        mutableStateOf(false)
    }

    var servicesExpanded by remember {
        mutableStateOf(false)
    }


    val courseOptions = remember {

        listOf(
            "4W Training",
            "2W Training",
            "2W + 4W Training"
        )
    }


    val serviceOptions = remember {

        listOf(
            "New Learner License",
            "LMV License",
            "LMV Endorsement",
            "Renewal",
            "Other"
        )
    }


    // =========================================================
    // FOCUS
    // =========================================================

    val addressFocusRequester =
        remember {
            FocusRequester()
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
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                )
    ) {

        // =====================================================
        // HEADER
        // =====================================================

        Row(

            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector =
                        Icons.AutoMirrored.Filled.ArrowBack,

                    contentDescription =
                        "Back to students"
                )
            }


            Spacer(
                modifier =
                    Modifier.width(4.dp)
            )


            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

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


                Text(
                    text =
                        "Create a new student profile",

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
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // PERSONAL DETAILS
        // =====================================================

        RegistrationSectionCard(
            title = "Personal details",
            subtitle = "Basic student information"
        ) {

            ModernTextField(
                value =
                    name,

                onValueChange = {
                    name = it
                    nameError = false
                },

                label =
                    "Student name",

                placeholder =
                    "Enter full name",

                icon =
                    Icons.Default.Badge,

                isError =
                    nameError,

                errorMessage =
                    if (nameError) {
                        "Student name is required"
                    } else {
                        null
                    },

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Text,

                        imeAction =
                            ImeAction.Next
                    ),

                keyboardActions =
                    KeyboardActions(
                        onNext = {
                            addressFocusRequester
                                .requestFocus()
                        }
                    )
            )


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            DateField(
                value =
                    admissionDate,

                isError =
                    dateError,

                onClick = {
                    showDatePicker = true
                }
            )


            if (dateError) {

                ValidationText(
                    text =
                        "Admission date is required"
                )
            }


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            TimeField(
                value =
                    trainingTime,

                isError =
                    timeError,

                onClick = {
                    showTimePicker = true
                }
            )


            if (timeError) {

                ValidationText(
                    text =
                        "Training time is required"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // TRAINING DETAILS
        // =====================================================

        RegistrationSectionCard(
            title = "Training details",
            subtitle = "Select course and service"
        ) {

            SelectionField(
                value =
                    course,

                label =
                    "Course",

                placeholder =
                    "Select course",

                icon =
                    Icons.Default.DirectionsCar,

                isError =
                    courseError,

                errorMessage =
                    if (courseError) {
                        "Please select a course"
                    } else {
                        null
                    },

                expanded =
                    courseExpanded,

                onClick = {
                    courseExpanded = true
                },

                onDismiss = {
                    courseExpanded = false
                },

                options =
                    courseOptions,

                onOptionSelected = {
                    course = it
                    courseError = false
                }
            )


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            SelectionField(
                value =
                    services,

                label =
                    "Service",

                placeholder =
                    "Select service",

                icon =
                    Icons.Default.Badge,

                isError =
                    serviceError,

                errorMessage =
                    if (serviceError) {
                        "Please select a service"
                    } else {
                        null
                    },

                expanded =
                    servicesExpanded,

                onClick = {
                    servicesExpanded = true
                },

                onDismiss = {
                    servicesExpanded = false
                },

                options =
                    serviceOptions,

                onOptionSelected = {
                    services = it
                    serviceError = false
                }
            )
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // CONTACT DETAILS
        // =====================================================

        RegistrationSectionCard(
            title = "Contact details",
            subtitle = "Student contact information"
        ) {

            ModernTextField(
                value =
                    address,

                onValueChange = {
                    address = it
                },

                label =
                    "Address",

                placeholder =
                    "Enter complete address",

                icon =
                    Icons.Default.LocationOn,

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .focusRequester(
                            addressFocusRequester
                        ),

                singleLine =
                    false,

                maxLines =
                    4,

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Text,

                        imeAction =
                            ImeAction.Next
                    )
            )


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            ModernTextField(
                value =
                    mobile,

                onValueChange = { value ->

                    if (
                        value.length <= 10 &&
                        value.all {
                            it.isDigit()
                        }
                    ) {
                        mobile = value
                        mobileError = false
                    }
                },

                label =
                    "Mobile number",

                placeholder =
                    "10-digit mobile number",

                icon =
                    Icons.Default.Phone,

                isError =
                    mobileError,

                errorMessage =
                    if (mobileError) {
                        "Enter a valid 10-digit mobile number"
                    } else {
                        null
                    },

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Phone,

                        imeAction =
                            ImeAction.Next
                    )
            )
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // FEES
        // =====================================================

        RegistrationSectionCard(
            title = "Fee details",
            subtitle = "Course fee information"
        ) {

            ModernTextField(
                value =
                    totalFees,

                onValueChange = { value ->

                    if (
                        value.length <= 10 &&
                        value.all {
                            it.isDigit() ||
                                    it == '.'
                        }
                    ) {

                        totalFees = value
                        feesError = false
                    }
                },

                label =
                    "Total fees",

                placeholder =
                    "Enter total fees",

                icon =
                    Icons.Default.AccountBalanceWallet,

                isError =
                    feesError,

                errorMessage =
                    if (feesError) {
                        "Please enter the total fees"
                    } else {
                        null
                    },

                keyboardOptions =
                    KeyboardOptions(
                        keyboardType =
                            KeyboardType.Decimal,

                        imeAction =
                            ImeAction.Done
                    )
            )
        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // SAVE BUTTON
        // =====================================================

        FilledTonalButton(

            onClick = {

                val valid =
                    validateForm(
                        name = name,
                        admissionDate = admissionDate,
                        trainingTime = trainingTime,
                        course = course,
                        services = services,
                        mobile = mobile,
                        totalFees = totalFees,

                        onNameError = {
                            nameError = true
                        },

                        onDateError = {
                            dateError = true
                        },

                        onTimeError = {
                            timeError = true
                        },

                        onCourseError = {
                            courseError = true
                        },

                        onServiceError = {
                            serviceError = true
                        },

                        onMobileError = {
                            mobileError = true
                        },

                        onFeesError = {
                            feesError = true
                        }
                    )


                if (valid) {

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
                }
            },

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(54.dp),

            shape =
                RoundedCornerShape(16.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Check,

                contentDescription =
                    null
            )


            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )


            Text(
                text =
                    "Save student",

                fontWeight =
                    FontWeight.SemiBold
            )
        }


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        // =====================================================
        // FINGERPRINT INFO
        // =====================================================

        FingerprintInfoCard()


        Spacer(
            modifier =
                Modifier.height(24.dp)
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

                                dateError = false
                            }

                        showDatePicker = false
                    }
                ) {

                    Text("Select")
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
                state =
                    datePickerState
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

                Text(
                    text =
                        "Training time"
                )
            },

            text = {

                TimePicker(
                    state =
                        timePickerState
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

                        trainingTime =
                            String.format(
                                Locale.getDefault(),
                                "%02d:%02d %s",
                                displayHour,
                                minute,
                                amPm
                            )

                        timeError = false

                        showTimePicker = false
                    }
                ) {

                    Text("Select")
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
// SECTION CARD
// =============================================================

@Composable
private fun RegistrationSectionCard(
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme
                        .colorScheme
                        .surfaceContainer
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
    ) {

        Column(

            modifier =
                Modifier.padding(18.dp),

            content = {

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


                Spacer(
                    modifier =
                        Modifier.height(3.dp)
                )


                Text(
                    text =
                        subtitle,

                    style =
                        MaterialTheme
                            .typography
                            .bodySmall,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )


                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )


                content()
            }
        )
    }
}


// =============================================================
// MODERN TEXT FIELD
// =============================================================

@Composable
private fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions.Default,
    keyboardActions: KeyboardActions =
        KeyboardActions.Default
) {

    OutlinedTextField(

        value =
            value,

        onValueChange =
            onValueChange,

        modifier =
            modifier.fillMaxWidth(),

        label = {
            Text(label)
        },

        placeholder = {
            Text(placeholder)
        },

        leadingIcon = {

            Icon(
                imageVector =
                    icon,

                contentDescription =
                    null
            )
        },

        isError =
            isError,

        supportingText = {

            if (
                isError &&
                errorMessage != null
            ) {

                Text(
                    text =
                        errorMessage
                )
            }
        },

        singleLine =
            singleLine,

        maxLines =
            maxLines,

        shape =
            RoundedCornerShape(14.dp),

        keyboardOptions =
            keyboardOptions,

        keyboardActions =
            keyboardActions
    )
}


// =============================================================
// DATE FIELD
// =============================================================

@Composable
private fun DateField(
    value: String,
    isError: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
    ) {

        OutlinedTextField(

            value =
                value,

            onValueChange = {},

            readOnly =
                true,

            enabled =
                false,

            label = {
                Text("Admission date")
            },

            placeholder = {
                Text("Select admission date")
            },

            leadingIcon = {

                Icon(
                    imageVector =
                        Icons.Default.CalendarMonth,

                    contentDescription =
                        null
                )
            },

            trailingIcon = {

                Icon(
                    imageVector =
                        Icons.Default.CalendarMonth,

                    contentDescription =
                        "Select date"
                )
            },

            isError =
                isError,

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(14.dp)
        )
    }
}


// =============================================================
// TIME FIELD
// =============================================================

@Composable
private fun TimeField(
    value: String,
    isError: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
    ) {

        OutlinedTextField(

            value =
                value,

            onValueChange = {},

            readOnly =
                true,

            enabled =
                false,

            label = {
                Text("Training time")
            },

            placeholder = {
                Text("Select training time")
            },

            leadingIcon = {

                Icon(
                    imageVector =
                        Icons.Default.Schedule,

                    contentDescription =
                        null
                )
            },

            trailingIcon = {

                Icon(
                    imageVector =
                        Icons.Default.Schedule,

                    contentDescription =
                        "Select time"
                )
            },

            isError =
                isError,

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(14.dp)
        )
    }
}


// =============================================================
// SELECTION FIELD
// =============================================================

@Composable
private fun SelectionField(
    value: String,
    label: String,
    placeholder: String,
    icon: ImageVector,
    isError: Boolean,
    errorMessage: String?,
    expanded: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {

    Box(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(

            value =
                value,

            onValueChange = {},

            readOnly =
                true,

            enabled =
                false,

            label = {
                Text(label)
            },

            placeholder = {
                Text(placeholder)
            },

            leadingIcon = {

                Icon(
                    imageVector =
                        icon,

                    contentDescription =
                        null
                )
            },

            trailingIcon = {

                Icon(
                    imageVector =
                        Icons.Default.ExpandMore,

                    contentDescription =
                        "Select $label"
                )
            },

            isError =
                isError,

            supportingText = {

                if (
                    isError &&
                    errorMessage != null
                ) {

                    Text(
                        text =
                            errorMessage
                    )
                }
            },

            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    },

            shape =
                RoundedCornerShape(14.dp)
        )


        DropdownMenu(

            expanded =
                expanded,

            onDismissRequest =
                onDismiss,

            modifier =
                Modifier.fillMaxWidth(0.85f)
        ) {

            options.forEach { option ->

                DropdownMenuItem(

                    text = {

                        Text(
                            text =
                                option,

                            maxLines = 1,

                            overflow =
                                TextOverflow.Ellipsis
                        )
                    },

                    onClick = {

                        onOptionSelected(
                            option
                        )

                        onDismiss()
                    }
                )
            }
        }
    }
}


// =============================================================
// VALIDATION TEXT
// =============================================================

@Composable
private fun ValidationText(
    text: String
) {

    Text(

        text =
            text,

        modifier =
            Modifier.padding(
                start = 16.dp,
                top = 5.dp
            ),

        style =
            MaterialTheme
                .typography
                .bodySmall,

        color =
            MaterialTheme
                .colorScheme
                .error
    )
}


// =============================================================
// FORM VALIDATION
// =============================================================

private fun validateForm(
    name: String,
    admissionDate: String,
    trainingTime: String,
    course: String,
    services: String,
    mobile: String,
    totalFees: String,
    onNameError: () -> Unit,
    onDateError: () -> Unit,
    onTimeError: () -> Unit,
    onCourseError: () -> Unit,
    onServiceError: () -> Unit,
    onMobileError: () -> Unit,
    onFeesError: () -> Unit
): Boolean {

    var valid = true


    if (name.isBlank()) {

        onNameError()
        valid = false
    }


    if (admissionDate.isBlank()) {

        onDateError()
        valid = false
    }


    if (trainingTime.isBlank()) {

        onTimeError()
        valid = false
    }


    if (course.isBlank()) {

        onCourseError()
        valid = false
    }


    if (services.isBlank()) {

        onServiceError()
        valid = false
    }


    if (
        mobile.length != 10 ||
        !mobile.all {
            it.isDigit()
        }
    ) {

        onMobileError()
        valid = false
    }


    if (totalFees.isBlank()) {

        onFeesError()
        valid = false
    }


    return valid
}


// =============================================================
// FINGERPRINT INFORMATION
// =============================================================

@Composable
private fun FingerprintInfoCard() {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(18.dp),

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

        Row(

            modifier =
                Modifier.padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(

                imageVector =
                    Icons.Default.Fingerprint,

                contentDescription =
                    null,

                modifier =
                    Modifier.size(30.dp),

                tint =
                    MaterialTheme
                        .colorScheme
                        .primary
            )


            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )


            Column {

                Text(

                    text =
                        "Fingerprint attendance",

                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,

                    fontWeight =
                        FontWeight.SemiBold
                )


                Spacer(
                    modifier =
                        Modifier.height(3.dp)
                )


                Text(

                    text =
                        "Fingerprint enrollment can be completed from the student profile after registration.",

                    style =
                        MaterialTheme
                            .typography
                            .bodySmall,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }
        }
    }
}