package com.proWheel.drive.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.dp
import com.proWheel.drive.data.Student


@Composable
fun StudentDetailsScreen(
    student: Student,

    onBack: () -> Unit,

    onEnrollFingerprint: () -> Unit
) {

    var currentPage by remember {
        mutableStateOf(1)
    }


    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp)
    ) {


        // =====================================================
        // BACK
        // =====================================================

        TextButton(
            onClick =
                onBack
        ) {

            Text(
                "← Back to Students"
            )
        }


        Spacer(
            modifier =
                Modifier.height(4.dp)
        )


        // =====================================================
        // STUDENT HEADER
        // =====================================================

        Card(

            modifier =
                Modifier.fillMaxWidth(),

            colors =
                CardDefaults
                    .cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .primaryContainer
                    )
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                Text(
                    text =
                        student.name,

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
                        "Student Profile"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(18.dp)
        )


        // =====================================================
        // PAGE TABS
        // =====================================================

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {

            if (currentPage == 1) {

                Button(

                    onClick = {
                        currentPage = 1
                    },

                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        "PAGE 1"
                    )
                }

            } else {

                OutlinedButton(

                    onClick = {
                        currentPage = 1
                    },

                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        "PAGE 1"
                    )
                }
            }


            if (currentPage == 2) {

                Button(

                    onClick = {
                        currentPage = 2
                    },

                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        "PAGE 2"
                    )
                }

            } else {

                OutlinedButton(

                    onClick = {
                        currentPage = 2
                    },

                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        "PAGE 2"
                    )
                }
            }
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // =====================================================
        // PAGE 1
        // =====================================================

        if (currentPage == 1) {

            Column(
                modifier =
                    Modifier.fillMaxSize()
            ) {

                Text(
                    text =
                        "ADMISSION INFORMATION",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )


                DetailField(
                    label =
                        "Admission Date",

                    value =
                        student.admissionDate
                )


                DetailField(
                    label =
                        "Student Name",

                    value =
                        student.name
                )


                DetailField(
                    label =
                        "Mobile Number",

                    value =
                        student.mobile
                )


                DetailField(
                    label =
                        "Address",

                    value =
                        student.address
                )


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )


                Button(

                    onClick = {
                        currentPage = 2
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        "NEXT →"
                    )
                }
            }
        }


        // =====================================================
        // PAGE 2
        // =====================================================

        if (currentPage == 2) {

            Column(
                modifier =
                    Modifier.fillMaxSize()
            ) {

                Text(
                    text =
                        "TRAINING & FEES",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )


                DetailField(
                    label =
                        "Course",

                    value =
                        student.course
                )


                DetailField(
                    label =
                        "Services",

                    value =
                        student.services
                )


                DetailField(
                    label =
                        "Training Timing",

                    value =
                        student.trainingTime
                )


                DetailField(
                    label =
                        "Total Fees",

                    value =
                        student.totalFees
                )


                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )


                // =================================================
                // FINGERPRINT
                // =================================================

                Text(
                    text =
                        "FINGERPRINT",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,

                    fontWeight =
                        FontWeight.Bold
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )


                Card(

                    modifier =
                        Modifier.fillMaxWidth(),

                    colors =
                        CardDefaults
                            .cardColors(
                                containerColor =
                                    MaterialTheme
                                        .colorScheme
                                        .surfaceVariant
                            )
                ) {

                    Column(
                        modifier =
                            Modifier.padding(18.dp)
                    ) {

                        Row(
                            modifier =
                                Modifier.fillMaxWidth(),

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Surface(

                                shape =
                                    MaterialTheme
                                        .shapes
                                        .large,

                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .errorContainer
                            ) {

                                Text(
                                    text =
                                        "  •  ",

                                    modifier =
                                        Modifier.padding(
                                            6.dp
                                        ),

                                    fontWeight =
                                        FontWeight.Bold
                                )
                            }

                            Spacer(
                                modifier =
                                    Modifier.padding(
                                        start = 10.dp
                                    )
                            )

                            Column {

                                Text(
                                    text =
                                        "Not Enrolled",

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Text(
                                    text =
                                        "Fingerprint has not been registered."
                                )
                            }
                        }


                        Spacer(
                            modifier =
                                Modifier.height(16.dp)
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
                    modifier =
                        Modifier.height(16.dp)
                )


                Button(

                    onClick = {
                        currentPage = 1
                    },

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        "← PREVIOUS"
                    )
                }
            }
        }
    }
}


// =============================================================
// DETAIL FIELD
// =============================================================

@Composable
fun DetailField(
    label: String,
    value: String
) {

    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 10.dp
                )
    ) {

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Text(
                text =
                    label,

                style =
                    MaterialTheme
                        .typography
                        .labelMedium
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    if (value.isBlank())
                        "Not provided"
                    else
                        value,

                style =
                    MaterialTheme
                        .typography
                        .bodyLarge,

                fontWeight =
                    FontWeight.Medium
            )
        }
    }
}