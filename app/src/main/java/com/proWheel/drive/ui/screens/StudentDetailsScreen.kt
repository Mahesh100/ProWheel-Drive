package com.proWheel.drive.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.proWheel.drive.data.Student


@Composable
fun StudentDetailsScreen(
    student: Student,
    onBack: () -> Unit,
    onEnrollFingerprint: () -> Unit
) {

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
        // TOP BAR
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
                        Icons.Default.ArrowBack,

                    contentDescription =
                        "Back to students"
                )
            }


            Spacer(
                modifier =
                    Modifier.width(4.dp)
            )


            Text(
                text = "Student Details",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge,

                fontWeight =
                    FontWeight.SemiBold,

                modifier =
                    Modifier.weight(1f)
            )
        }


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        // =====================================================
        // PROFILE HEADER
        // =====================================================

        StudentProfileHeader(
            student = student
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // PERSONAL INFORMATION
        // =====================================================

        SectionTitle(
            title = "Personal information"
        )


        Spacer(
            modifier =
                Modifier.height(10.dp)
        )


        DetailCard {

            DetailRow(
                icon =
                    Icons.Default.Phone,

                label =
                    "Mobile number",

                value =
                    student.mobile
            )


            DetailDivider()


            DetailRow(
                icon =
                    Icons.Default.LocationOn,

                label =
                    "Address",

                value =
                    student.address
            )


            DetailDivider()


            DetailRow(
                icon =
                    Icons.Default.CalendarMonth,

                label =
                    "Admission date",

                value =
                    student.admissionDate
            )
        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // TRAINING INFORMATION
        // =====================================================

        SectionTitle(
            title = "Training information"
        )


        Spacer(
            modifier =
                Modifier.height(10.dp)
        )


        DetailCard {

            DetailRow(
                icon =
                    Icons.Default.DirectionsCar,

                label =
                    "Course",

                value =
                    student.course
            )


            DetailDivider()


            DetailRow(
                icon =
                    Icons.Default.Settings,

                label =
                    "Service",

                value =
                    student.services
            )


            DetailDivider()


            DetailRow(
                icon =
                    Icons.Default.Schedule,

                label =
                    "Training time",

                value =
                    student.trainingTime
            )
        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // FEES
        // =====================================================

        SectionTitle(
            title = "Fee information"
        )


        Spacer(
            modifier =
                Modifier.height(10.dp)
        )


        FeesCard(
            totalFees =
                student.totalFees
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // FINGERPRINT
        // =====================================================

        SectionTitle(
            title = "Attendance"
        )


        Spacer(
            modifier =
                Modifier.height(10.dp)
        )


        FingerprintCard(
            onEnrollFingerprint =
                onEnrollFingerprint
        )


        Spacer(
            modifier =
                Modifier.height(28.dp)
        )


        // =====================================================
        // BACK BUTTON
        // =====================================================

        OutlinedButton(
            onClick = onBack,

            modifier =
                Modifier.fillMaxWidth(),

            shape =
                RoundedCornerShape(14.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.ArrowBack,

                contentDescription =
                    null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text =
                    "Back to students"
            )
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )
    }
}


// =============================================================
// PROFILE HEADER
// =============================================================

@Composable
private fun StudentProfileHeader(
    student: Student
) {

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
                        .primaryContainer
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
    ) {

        Row(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(22.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            StudentAvatar(
                name =
                    student.name
            )


            Spacer(
                modifier =
                    Modifier.width(16.dp)
            )


            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        student.name.ifBlank {
                            "Unnamed student"
                        },

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onPrimaryContainer,

                    maxLines = 2,

                    overflow =
                        TextOverflow.Ellipsis
                )


                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )


                Text(
                    text =
                        student.course.ifBlank {
                            "Driving student"
                        },

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onPrimaryContainer
                )


                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Surface(

                    shape =
                        RoundedCornerShape(50.dp),

                    color =
                        MaterialTheme
                            .colorScheme
                            .surface.copy(
                                alpha = 0.7f
                            )
                ) {

                    Text(
                        text =
                            "Student ID: ${student.id}",

                        modifier =
                            Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 5.dp
                            ),

                        style =
                            MaterialTheme
                                .typography
                                .labelMedium,

                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurface
                    )
                }
            }
        }
    }
}


// =============================================================
// AVATAR
// =============================================================

@Composable
private fun StudentAvatar(
    name: String
) {

    val initial =
        name
            .trim()
            .firstOrNull()
            ?.uppercase()
            ?: "S"


    Surface(

        modifier =
            Modifier.size(64.dp),

        shape =
            CircleShape,

        color =
            MaterialTheme
                .colorScheme
                .primary
    ) {

        Column(

            modifier =
                Modifier.fillMaxSize(),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(
                text =
                    initial,

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme
                        .colorScheme
                        .onPrimary
            )
        }
    }
}


// =============================================================
// SECTION TITLE
// =============================================================

@Composable
private fun SectionTitle(
    title: String
) {

    Text(
        text = title,

        style =
            MaterialTheme
                .typography
                .titleMedium,

        fontWeight =
            FontWeight.SemiBold
    )
}


// =============================================================
// DETAIL CARD
// =============================================================

@Composable
private fun DetailCard(
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
                Modifier.padding(16.dp),

            content = content
        )
    }
}


// =============================================================
// DETAIL ROW
// =============================================================

@Composable
private fun DetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {

    Row(

        modifier =
            Modifier.fillMaxWidth(),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Surface(

            modifier =
                Modifier.size(42.dp),

            shape =
                RoundedCornerShape(12.dp),

            color =
                MaterialTheme
                    .colorScheme
                    .secondaryContainer
        ) {

            Icon(
                imageVector =
                    icon,

                contentDescription =
                    null,

                modifier =
                    Modifier.padding(11.dp),

                tint =
                    MaterialTheme
                        .colorScheme
                        .onSecondaryContainer
            )
        }


        Spacer(
            modifier =
                Modifier.width(14.dp)
        )


        Column(
            modifier =
                Modifier.weight(1f)
        ) {

            Text(
                text =
                    label,

                style =
                    MaterialTheme
                        .typography
                        .labelMedium,

                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )


            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )


            Text(
                text =
                    value.ifBlank {
                        "Not provided"
                    },

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


// =============================================================
// DIVIDER
// =============================================================

@Composable
private fun DetailDivider() {

    HorizontalDivider(
        modifier =
            Modifier.padding(
                vertical = 14.dp
            ),

        color =
            MaterialTheme
                .colorScheme
                .outlineVariant
    )
}


// =============================================================
// FEES CARD
// =============================================================

@Composable
private fun FeesCard(
    totalFees: String
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
                        .tertiaryContainer
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
    ) {

        Row(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Surface(

                modifier =
                    Modifier.size(48.dp),

                shape =
                    RoundedCornerShape(14.dp),

                color =
                    MaterialTheme
                        .colorScheme
                        .tertiary
            ) {

                Icon(
                    imageVector =
                        Icons.Default.AccountBalanceWallet,

                    contentDescription =
                        null,

                    modifier =
                        Modifier.padding(12.dp),

                    tint =
                        MaterialTheme
                            .colorScheme
                            .onTertiary
                )
            }


            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )


            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        "Total fees",

                    style =
                        MaterialTheme
                            .typography
                            .labelMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onTertiaryContainer
                )


                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )


                Text(
                    text =
                        totalFees.ifBlank {
                            "Not provided"
                        },

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onTertiaryContainer
                )
            }
        }
    }
}


// =============================================================
// FINGERPRINT CARD
// =============================================================

@Composable
private fun FingerprintCard(
    onEnrollFingerprint: () -> Unit
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
                Modifier.padding(18.dp)
        ) {

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Surface(

                    modifier =
                        Modifier.size(50.dp),

                    shape =
                        CircleShape,

                    color =
                        MaterialTheme
                            .colorScheme
                            .errorContainer
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Fingerprint,

                        contentDescription =
                            null,

                        modifier =
                            Modifier.padding(12.dp),

                        tint =
                            MaterialTheme
                                .colorScheme
                                .onErrorContainer
                    )
                }


                Spacer(
                    modifier =
                        Modifier.width(14.dp)
                )


                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

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
                            "Not enrolled",

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium,

                        color =
                            MaterialTheme
                                .colorScheme
                                .error
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            Text(
                text =
                    "Enroll this student's fingerprint to enable fingerprint-based attendance.",

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
                    Modifier.height(16.dp)
            )


            FilledTonalButton(

                onClick =
                    onEnrollFingerprint,

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(14.dp)
            ) {

                Icon(
                    imageVector =
                        Icons.Default.Fingerprint,

                    contentDescription =
                        null
                )


                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )


                Text(
                    text =
                        "Enroll fingerprint",

                    fontWeight =
                        FontWeight.SemiBold
                )
            }
        }
    }
}