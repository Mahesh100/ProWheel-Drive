package com.proWheel.drive.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(

    onRegister:
        (
        String,
        String,
        String,
        String
    ) -> Unit,

    onBackToLogin:
        () -> Unit
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

        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(28.dp)
    ) {

        Text(
            text =
                "PRO WHEEL DRIVE",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium,

            fontWeight =
                FontWeight.Bold
        )


        Text(
            text =
                "Create New Account",

            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )


        Spacer(
            modifier =
                Modifier.height(28.dp)
        )


        OutlinedTextField(

            value =
                username,

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

            value =
                password,

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

            value =
                confirmPassword,

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

            value =
                mobile,

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

            Text(
                "REGISTER"
            )
        }


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

