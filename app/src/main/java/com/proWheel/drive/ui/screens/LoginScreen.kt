package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLogin: (String, String, Boolean) -> Unit,
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

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var usernameError by remember {
        mutableStateOf(false)
    }

    var passwordError by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }


    fun performLogin() {

        usernameError =
            username.isBlank()

        passwordError =
            password.isBlank()

        if (
            usernameError ||
            passwordError
        ) {
            return
        }

        isLoading = true

        onLogin(
            username.trim(),
            password,
            keepLoggedIn
        )

        /*
         * MainActivity owns the actual authentication flow.
         * This keeps the existing Room/database logic unchanged.
         */
        isLoading = false
    }


    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .imePadding()
                .navigationBarsPadding()
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

        Spacer(
            modifier =
                Modifier.height(28.dp)
        )


        // =====================================================
        // BRAND
        // =====================================================

        Surface(

            modifier =
                Modifier.size(72.dp),

            shape =
                MaterialTheme
                    .shapes
                    .large,

            color =
                MaterialTheme
                    .colorScheme
                    .primaryContainer
        ) {

            Box(
                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = "PW",

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onPrimaryContainer
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(18.dp)
        )


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
                Modifier.height(32.dp)
        )


        // =====================================================
        // LOGIN CARD
        // =====================================================

        Card(

            modifier =
                Modifier.fillMaxWidth(),

            elevation =
                CardDefaults
                    .cardElevation(
                        defaultElevation =
                            2.dp
                    )
        ) {

            Column(

                modifier =
                    Modifier.padding(
                        22.dp
                    )
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
                        Modifier.height(6.dp)
                )


                Text(

                    text =
                        "Sign in to continue",

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
                        Modifier.height(24.dp)
                )


                // =================================================
                // USERNAME
                // =================================================

                OutlinedTextField(

                    value =
                        username,

                    onValueChange = {

                        username = it

                        if (it.isNotBlank()) {
                            usernameError = false
                        }
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    label = {
                        Text("Username")
                    },

                    singleLine = true,

                    isError =
                        usernameError,

                    supportingText = {

                        if (usernameError) {

                            Text(
                                "Username is required"
                            )
                        }
                    },

                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Text,

                            imeAction =
                                ImeAction.Next
                        ),

                    enabled =
                        !isLoading
                )


                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )


                // =================================================
                // PASSWORD
                // =================================================

                OutlinedTextField(

                    value =
                        password,

                    onValueChange = {

                        password = it

                        if (it.isNotBlank()) {
                            passwordError = false
                        }
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    label = {
                        Text("Password")
                    },

                    singleLine = true,

                    isError =
                        passwordError,

                    supportingText = {

                        if (passwordError) {

                            Text(
                                "Password is required"
                            )
                        }
                    },

                    visualTransformation =

                        if (passwordVisible)

                            VisualTransformation.None

                        else

                            PasswordVisualTransformation(),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                passwordVisible =
                                    !passwordVisible
                            },

                            enabled =
                                !isLoading
                        ) {

                            Icon(

                                imageVector =

                                    if (passwordVisible)

                                        Icons.Default.VisibilityOff

                                    else

                                        Icons.Default.Visibility,

                                contentDescription =

                                    if (passwordVisible)

                                        "Hide password"

                                    else

                                        "Show password"
                            )
                        }
                    },

                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType =
                                KeyboardType.Password,

                            imeAction =
                                ImeAction.Done
                        ),

                    enabled =
                        !isLoading
                )


                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )


                // =================================================
                // KEEP LOGGED IN
                // =================================================

                Row(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled =
                                    !isLoading
                            ) {

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
                        },

                        enabled =
                            !isLoading
                    )


                    Text(

                        text =
                            "Keep me signed in",

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )


                // =================================================
                // LOGIN BUTTON
                // =================================================

                Button(

                    onClick =
                        ::performLogin,

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(52.dp),

                    enabled =
                        !isLoading
                ) {

                    if (isLoading) {

                        CircularProgressIndicator(

                            modifier =
                                Modifier.size(20.dp),

                            strokeWidth =
                                2.dp
                        )

                        Spacer(
                            modifier =
                                Modifier.width(10.dp)
                        )

                        Text(
                            "Signing in..."
                        )

                    } else {

                        Text(
                            "SIGN IN"
                        )
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )


                // =================================================
                // REGISTER
                // =================================================

                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.Center,

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
                            onRegister,

                        enabled =
                            !isLoading
                    ) {

                        Text(
                            "Create account"
                        )
                    }
                }
            }
        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Text(

            text =
                "Fast • Simple • Secure",

            style =
                MaterialTheme
                    .typography
                    .labelMedium,

            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )
    }
}

