package com.proWheel.drive.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun RegisterScreen(
    onRegister: (
        String,
        String,
        String,
        String
    ) -> Unit,

    onBackToLogin: () -> Unit
) {

    // =========================================================
    // FORM STATE
    // =========================================================

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


    // =========================================================
    // PASSWORD VISIBILITY
    // =========================================================

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // VALIDATION STATE
    // =========================================================

    var usernameError by remember {
        mutableStateOf(false)
    }

    var passwordError by remember {
        mutableStateOf(false)
    }

    var confirmPasswordError by remember {
        mutableStateOf(false)
    }

    var mobileError by remember {
        mutableStateOf(false)
    }


    // =========================================================
    // FOCUS
    // =========================================================

    val passwordFocusRequester =
        remember {
            FocusRequester()
        }

    val confirmPasswordFocusRequester =
        remember {
            FocusRequester()
        }

    val mobileFocusRequester =
        remember {
            FocusRequester()
        }


    val keyboardController =
        LocalSoftwareKeyboardController.current


    // =========================================================
    // SCREEN
    // =========================================================

    Surface(

        modifier =
            Modifier.fillMaxSize(),

        color =
            MaterialTheme
                .colorScheme
                .background
    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        horizontal = 24.dp,
                        vertical = 20.dp
                    ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            // =================================================
            // BACK BUTTON
            // =================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                IconButton(

                    onClick =
                        onBackToLogin
                ) {

                    Icon(

                        imageVector =
                            Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription =
                            "Back to login"
                    )
                }


                Spacer(
                    modifier =
                        Modifier.width(4.dp)
                )


                Text(

                    text =
                        "Create account",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,

                    fontWeight =
                        FontWeight.SemiBold
                )
            }


            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            // =================================================
            // APP ICON
            // =================================================

            Surface(

                modifier =
                    Modifier.size(72.dp),

                shape =
                    CircleShape,

                color =
                    MaterialTheme
                        .colorScheme
                        .primaryContainer
            ) {

                Icon(

                    imageVector =
                        Icons.Default.School,

                    contentDescription =
                        null,

                    modifier =
                        Modifier.padding(20.dp),

                    tint =
                        MaterialTheme
                            .colorScheme
                            .onPrimaryContainer
                )
            }


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            // =================================================
            // TITLE
            // =================================================

            Text(

                text =
                    "PRO WHEEL DRIVE",

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )


            Text(

                text =
                    "Set up your driving school account",

                style =
                    MaterialTheme
                        .typography
                        .bodyLarge,

                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )


            // =================================================
            // REGISTRATION CARD
            // =================================================

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(24.dp),

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
                        Modifier.padding(20.dp)
                ) {

                    Text(

                        text =
                            "Account details",

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
                            "Enter your details to create an account.",

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
                            Modifier.height(22.dp)
                    )


                    // =========================================
                    // USERNAME
                    // =========================================

                    RegisterTextField(

                        value =
                            username,

                        onValueChange = {

                            username =
                                it

                            usernameError =
                                false
                        },

                        label =
                            "Username",

                        placeholder =
                            "Enter username",

                        icon =
                            Icons.Default.Person,

                        isError =
                            usernameError,

                        errorMessage =
                            if (usernameError) {
                                "Username is required"
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

                                    passwordFocusRequester
                                        .requestFocus()
                                }
                            )
                    )


                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )


                    // =========================================
                    // PASSWORD
                    // =========================================

                    RegisterTextField(

                        value =
                            password,

                        onValueChange = {

                            password =
                                it

                            passwordError =
                                false

                            if (
                                confirmPassword.isNotBlank() &&
                                confirmPassword == it
                            ) {

                                confirmPasswordError =
                                    false
                            }
                        },

                        label =
                            "Password",

                        placeholder =
                            "Enter password",

                        icon =
                            Icons.Default.Lock,

                        modifier =
                            Modifier.focusRequester(
                                passwordFocusRequester
                            ),

                        isError =
                            passwordError,

                        errorMessage =
                            if (passwordError) {
                                "Password is required"
                            } else {
                                null
                            },

                        visualTransformation =
                            if (passwordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },

                        trailingContent = {

                            IconButton(

                                onClick = {

                                    passwordVisible =
                                        !passwordVisible
                                }
                            ) {

                                Icon(

                                    imageVector =
                                        if (passwordVisible) {
                                            Icons.Default.VisibilityOff
                                        } else {
                                            Icons.Default.Visibility
                                        },

                                    contentDescription =
                                        if (passwordVisible) {
                                            "Hide password"
                                        } else {
                                            "Show password"
                                        }
                                )
                            }
                        },

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Password,

                                imeAction =
                                    ImeAction.Next
                            ),

                        keyboardActions =
                            KeyboardActions(
                                onNext = {

                                    confirmPasswordFocusRequester
                                        .requestFocus()
                                }
                            )
                    )


                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )


                    // =========================================
                    // CONFIRM PASSWORD
                    // =========================================

                    RegisterTextField(

                        value =
                            confirmPassword,

                        onValueChange = {

                            confirmPassword =
                                it

                            confirmPasswordError =
                                false
                        },

                        label =
                            "Confirm password",

                        placeholder =
                            "Re-enter password",

                        icon =
                            Icons.Default.Lock,

                        modifier =
                            Modifier.focusRequester(
                                confirmPasswordFocusRequester
                            ),

                        isError =
                            confirmPasswordError,

                        errorMessage =
                            when {

                                confirmPasswordError &&
                                        confirmPassword.isBlank() ->
                                    "Please confirm your password"

                                confirmPasswordError ->
                                    "Passwords do not match"

                                else ->
                                    null
                            },

                        visualTransformation =
                            if (confirmPasswordVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },

                        trailingContent = {

                            IconButton(

                                onClick = {

                                    confirmPasswordVisible =
                                        !confirmPasswordVisible
                                }
                            ) {

                                Icon(

                                    imageVector =
                                        if (confirmPasswordVisible) {
                                            Icons.Default.VisibilityOff
                                        } else {
                                            Icons.Default.Visibility
                                        },

                                    contentDescription =
                                        if (confirmPasswordVisible) {
                                            "Hide password"
                                        } else {
                                            "Show password"
                                        }
                                )
                            }
                        },

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Password,

                                imeAction =
                                    ImeAction.Next
                            ),

                        keyboardActions =
                            KeyboardActions(
                                onNext = {

                                    mobileFocusRequester
                                        .requestFocus()
                                }
                            )
                    )


                    Spacer(
                        modifier =
                            Modifier.height(14.dp)
                    )


                    // =========================================
                    // MOBILE
                    // =========================================

                    RegisterTextField(

                        value =
                            mobile,

                        onValueChange = { value ->

                            if (
                                value.length <= 10 &&
                                value.all {
                                    it.isDigit()
                                }
                            ) {

                                mobile =
                                    value

                                mobileError =
                                    false
                            }
                        },

                        label =
                            "Mobile number",

                        placeholder =
                            "10-digit mobile number",

                        icon =
                            Icons.Default.Phone,

                        modifier =
                            Modifier.focusRequester(
                                mobileFocusRequester
                            ),

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
                                    ImeAction.Done
                            ),

                        keyboardActions =
                            KeyboardActions(
                                onDone = {

                                    keyboardController
                                        ?.hide()

                                    performRegistration(
                                        username =
                                            username,

                                        password =
                                            password,

                                        confirmPassword =
                                            confirmPassword,

                                        mobile =
                                            mobile,

                                        onUsernameError = {
                                            usernameError =
                                                true
                                        },

                                        onPasswordError = {
                                            passwordError =
                                                true
                                        },

                                        onConfirmPasswordError = {
                                            confirmPasswordError =
                                                true
                                        },

                                        onMobileError = {
                                            mobileError =
                                                true
                                        },

                                        onRegister =
                                            onRegister
                                    )
                                }
                            )
                    )


                    Spacer(
                        modifier =
                            Modifier.height(22.dp)
                    )


                    // =========================================
                    // REGISTER BUTTON
                    // =========================================

                    Button(

                        onClick = {

                            keyboardController
                                ?.hide()

                            performRegistration(

                                username =
                                    username,

                                password =
                                    password,

                                confirmPassword =
                                    confirmPassword,

                                mobile =
                                    mobile,

                                onUsernameError = {
                                    usernameError =
                                        true
                                },

                                onPasswordError = {
                                    passwordError =
                                        true
                                },

                                onConfirmPasswordError = {
                                    confirmPasswordError =
                                        true
                                },

                                onMobileError = {
                                    mobileError =
                                        true
                                },

                                onRegister =
                                    onRegister
                            )
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(54.dp),

                        shape =
                            RoundedCornerShape(16.dp)
                    ) {

                        Text(

                            text =
                                "Create account",

                            fontWeight =
                                FontWeight.SemiBold
                        )


                        Spacer(
                            modifier =
                                Modifier.width(8.dp)
                        )


                        Icon(

                            imageVector =
                                Icons.Default.ArrowForward,

                            contentDescription =
                                null
                        )
                    }
                }
            }


            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )


            // =================================================
            // LOGIN LINK
            // =================================================

            Row(

                horizontalArrangement =
                    Arrangement.Center,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text =
                        "Already have an account?",

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )


                TextButton(

                    onClick =
                        onBackToLogin
                ) {

                    Text(

                        text =
                            "Sign in",

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            // =================================================
            // SECURITY MESSAGE
            // =================================================

            Row(

                horizontalArrangement =
                    Arrangement.Center,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(

                    imageVector =
                        Icons.Default.Check,

                    contentDescription =
                        null,

                    modifier =
                        Modifier.size(16.dp),

                    tint =
                        MaterialTheme
                            .colorScheme
                            .primary
                )


                Spacer(
                    modifier =
                        Modifier.width(6.dp)
                )


                Text(

                    text =
                        "Your account information stays on this device.",

                    style =
                        MaterialTheme
                            .typography
                            .labelMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant,

                    textAlign =
                        TextAlign.Center
                )
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )
        }
    }
}


// =============================================================
// TEXT FIELD
// =============================================================

@Composable
private fun RegisterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation =
        VisualTransformation.None,
    trailingContent: @Composable (() -> Unit)? = null,
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

        trailingIcon =
            trailingContent,

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
            true,

        shape =
            RoundedCornerShape(14.dp),

        visualTransformation =
            visualTransformation,

        keyboardOptions =
            keyboardOptions,

        keyboardActions =
            keyboardActions
    )
}


// =============================================================
// REGISTRATION VALIDATION
// =============================================================

private fun performRegistration(
    username: String,
    password: String,
    confirmPassword: String,
    mobile: String,
    onUsernameError: () -> Unit,
    onPasswordError: () -> Unit,
    onConfirmPasswordError: () -> Unit,
    onMobileError: () -> Unit,
    onRegister: (
        String,
        String,
        String,
        String
    ) -> Unit
) {

    var isValid =
        true


    // ---------------------------------------------------------
    // USERNAME
    // ---------------------------------------------------------

    if (
        username.isBlank()
    ) {

        onUsernameError()

        isValid =
            false
    }


    // ---------------------------------------------------------
    // PASSWORD
    // ---------------------------------------------------------

    if (
        password.isBlank()
    ) {

        onPasswordError()

        isValid =
            false
    }


    // ---------------------------------------------------------
    // CONFIRM PASSWORD
    // ---------------------------------------------------------

    if (
        confirmPassword.isBlank() ||
        password != confirmPassword
    ) {

        onConfirmPasswordError()

        isValid =
            false
    }


    // ---------------------------------------------------------
    // MOBILE
    // ---------------------------------------------------------

    if (
        mobile.length != 10 ||
        !mobile.all {
            it.isDigit()
        }
    ) {

        onMobileError()

        isValid =
            false
    }


    if (!isValid) {
        return
    }


    // ---------------------------------------------------------
    // SUBMIT
    // ---------------------------------------------------------

    onRegister(

        username.trim(),

        password,

        confirmPassword,

        mobile
    )
}