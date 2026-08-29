package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(

    username: String,

    studentCount: Int,

    modifier: Modifier =
        Modifier,

    onOpenStudents:
        () -> Unit
) {

    Column(

        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(20.dp)
    ) {

        Text(

            text =
                "Welcome, $username",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall,

            fontWeight =
                FontWeight.Bold
        )


        Text(

            text =
                "Manage your driving school",

            style =
                MaterialTheme
                    .typography
                    .bodyLarge
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // STUDENTS CARD
        // =====================================================

        Card(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        onOpenStudents()
                    },

            colors =
                CardDefaults.cardColors(

                    containerColor =
                        MaterialTheme
                            .colorScheme
                            .primaryContainer
                )
        ) {

            Column(

                modifier =
                    Modifier.padding(22.dp)
            ) {

                Text(

                    text =
                        "REGISTERED STUDENTS",

                    style =
                        MaterialTheme
                            .typography
                            .labelLarge
                )


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Text(

                    text =
                        studentCount.toString(),

                    style =
                        MaterialTheme
                            .typography
                            .displaySmall,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(
                    text =
                        "Tap to view students"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(16.dp)
        )


        // =====================================================
        // ATTENDANCE CARD
        // =====================================================

        Card(

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(

                modifier =
                    Modifier.padding(22.dp)
            ) {

                Text(

                    text =
                        "TODAY'S ATTENDANCE",

                    style =
                        MaterialTheme
                            .typography
                            .labelLarge
                )


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Text(

                    text =
                        "Coming soon",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(

                    text =
                        "Fingerprint based attendance will be added."
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(16.dp)
        )


        // =====================================================
        // TRAINING CARD
        // =====================================================

        Card(

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(

                modifier =
                    Modifier.padding(22.dp)
            ) {

                Text(

                    text =
                        "TODAY'S TRAINING",

                    style =
                        MaterialTheme
                            .typography
                            .labelLarge
                )


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Text(

                    text =
                        "Training schedule",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(

                    text =
                        "Training schedule management will be added next."
                )
            }
        }
    }
}
