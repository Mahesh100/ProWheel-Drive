package com.proWheel.drive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proWheel.drive.data.Student


@Composable
fun StudentDetailsScreen(
    student: Student,
    onBack: () -> Unit,
    onEnrollFingerprint: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) {

        // =====================================================
        // BACK
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
            text = student.name,

            style =
                MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Student Details",

            style =
                MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // =====================================================
        // PAGE 1 — PERSONAL / ADMISSION
        // =====================================================

        Text(
            text = "Page 1 — Student Information",

            style =
                MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        StudentDetailCard(
            title = "Student Name",
            value = student.name
        )

        StudentDetailCard(
            title = "Mobile Number",
            value = student.mobile
        )

        StudentDetailCard(
            title = "Address",
            value = student.address
        )

        StudentDetailCard(
            title = "Admission Date",
            value = student.admissionDate
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // =====================================================
        // PAGE 2 — TRAINING / FEES
        // =====================================================

        Text(
            text = "Page 2 — Training & Fees",

            style =
                MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        StudentDetailCard(
            title = "Course",
            value = student.course
        )

        StudentDetailCard(
            title = "Services",
            value = student.services
        )

        StudentDetailCard(
            title = "Training Timing",
            value = student.trainingTime
        )

        StudentDetailCard(
            title = "Total Fees",
            value = student.totalFees
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // =====================================================
        // FINGERPRINT
        // =====================================================

        Text(
            text = "Fingerprint",

            style =
                MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Card(
            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(
                modifier =
                    Modifier.padding(16.dp)
            ) {

                Text(
                    text =
                        "Fingerprint not enrolled"
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Button(

                    onClick =
                        onEnrollFingerprint,

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        "ENROLL FINGERPRINT"
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}


// =============================================================
// DETAIL CARD
// =============================================================

@Composable
fun StudentDetailCard(
    title: String,
    value: String
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp
                )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(
                text = title,

                style =
                    MaterialTheme
                        .typography
                        .labelLarge
            )

            Text(
                text = value,

                style =
                    MaterialTheme
                        .typography
                        .bodyLarge
            )
        }
    }
}