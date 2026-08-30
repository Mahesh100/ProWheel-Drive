package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(
    onLogin: (String, String, Boolean) -> Unit,
    onRegister: () -> Unit
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

    var keepLoggedIn by remember {
        mutableStateOf(true)
    }

    var passwordVisible by remember {
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


    // =========================================================
    // KEYBOARD / FOCUS
    // =========================================================

    val passwordFocusRequester =
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
                    .imePadding()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 24.dp
                    ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier =
                    Modifier.weight(
                        1f,
                        fill = false
                    )
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
                    Modifier.height(18.dp)
            )


            // =================================================
            // BRAND
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
                    "Motor Driving School",

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
                    Modifier.height(28.dp)
            )


            // =================================================
            // LOGIN CARD
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
                            "Welcome back",

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
                            "Sign in to continue managing your driving school.",

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

                    ModernLoginField(

                        value =
                            username,

                        onValueChange = {
                            username = it
                            usernameError = false
                        },

                        label =
                            "Username",

                        placeholder =
                            "Enter your username",

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

                    ModernLoginField(

                        value =
                            password,

                        onValueChange = {
                            password = it
                            passwordError = false
                        },

                        label =
                            "Password",

                        placeholder =
                            "Enter your password",

                        icon =
                            Icons.Default.Lock,

                        isError =
                            passwordError,

                        errorMessage =
                            if (passwordError) {
                                "Password is required"
                            } else {
                                null
                            },

                        modifier =
                            Modifier.focusRequester(
                                passwordFocusRequester
                            ),

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
                                imeAction =
                                    ImeAction.Done
                            ),

                        keyboardActions =
                            KeyboardActions(
                                onDone = {

                                    keyboardController
                                        ?.hide()

                                    if (
                                        username.isNotBlank() &&
                                        password.isNotBlank()
                                    ) {

                                        onLogin(
                                            username.trim(),
                                            password,
                                            keepLoggedIn
                                        )
                                    } else {

                                        usernameError =
                                            username.isBlank()

                                        passwordError =
                                            password.isBlank()
                                    }
                                }
                            )
                    )


                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )


                    // =========================================
                    // KEEP LOGGED IN
                    // =========================================

                    Row(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickableWithoutRipple {
                                    keepLoggedIn =
                                        !keepLoggedIn
                                },

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Checkbox(

                            checked =
                                keepLoggedIn,

                            onCheckedChange = {
                                keepLoggedIn =
                                    it
                            }
                        )


                        Spacer(
                            modifier =
                                Modifier.width(4.dp)
                        )


                        Text(
                            text =
                                "Keep me logged in",

                            style =
                                MaterialTheme
                                    .typography
                                    .bodyMedium,

                            modifier =
                                Modifier.weight(1f)
                        )
                    }


                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )


                    // =========================================
                    // LOGIN BUTTON
                    // =========================================

                    Button(

                        onClick = {

                            usernameError =
                                username.isBlank()

                            passwordError =
                                password.isBlank()

                            if (
                                usernameError ||
                                passwordError
                            ) {
                                return@Button
                            }


                            keyboardController
                                ?.hide()


                            onLogin(
                                username.trim(),
                                password,
                                keepLoggedIn
                            )
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .size(
                                    height = 54.dp,
                                    width = 0.dp
                                ),

                        shape =
                            RoundedCornerShape(16.dp)
                    ) {

                        Text(
                            text =
                                "Sign in",

                            fontWeight =
                                FontWeight.SemiBold
                        )


                        Spacer(
                            modifier =
                                Modifier.width(8.dp)
                        )


                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowForward,

                            contentDescription =
                                null
                        )
                    }
                }
            }


            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )


            // =================================================
            // REGISTER
            // =================================================

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text =
                        "New to Pro Wheel Drive?",

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
                        onRegister
                ) {

                    Text(
                        text =
                            "Create account",

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            // =================================================
            // SECURITY NOTE
            // =================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

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
                        "Your login details are securely handled.",

                    style =
                        MaterialTheme
                            .typography
                            .labelMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant,

                    textAlign =
                        TextAlign.Center,

                    maxLines = 2,

                    overflow =
                        TextOverflow.Ellipsis
                )
            }


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )
        }
    }
}


// =============================================================
// MODERN LOGIN FIELD
// =============================================================

@Composable
private fun ModernLoginField(
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
// CLICK WITHOUT RIPPLE
// =============================================================

private fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit
): Modifier {

    return this.then(
        Modifier
            .clickable(
                indication = null,
                interactionSource =
                    androidx.compose.foundation
                        .interaction
                        .MutableInteractionSource(),
                onClick = onClick
            )
    )
}