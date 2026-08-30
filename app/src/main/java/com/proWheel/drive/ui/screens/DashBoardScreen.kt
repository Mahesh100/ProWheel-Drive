package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun DashboardScreen(
    username: String,
    studentCount: Int,
    modifier: Modifier = Modifier,
    onOpenStudents: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {

        // =====================================================
        // WELCOME HEADER
        // =====================================================

        Text(
            text = getGreeting(),
            style =
                MaterialTheme
                    .typography
                    .labelLarge,

            color =
                MaterialTheme
                    .colorScheme
                    .primary,

            fontWeight =
                FontWeight.SemiBold
        )

        Spacer(
            modifier =
                Modifier.height(4.dp)
        )

        Text(
            text = username.ifBlank {
                "Welcome"
            },

            style =
                MaterialTheme
                    .typography
                    .headlineMedium,

            fontWeight =
                FontWeight.Bold,

            maxLines = 1,

            overflow =
                TextOverflow.Ellipsis
        )

        Spacer(
            modifier =
                Modifier.height(4.dp)
        )

        Text(
            text =
                "Manage your driving school with ease.",

            style =
                MaterialTheme
                    .typography
                    .bodyLarge,

            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // STUDENT OVERVIEW
        // =====================================================

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        onOpenStudents()
                    },

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

                Surface(
                    modifier =
                        Modifier.size(58.dp),

                    shape =
                        RoundedCornerShape(18.dp),

                    color =
                        MaterialTheme
                            .colorScheme
                            .primary
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Groups,

                        contentDescription =
                            null,

                        modifier =
                            Modifier.padding(15.dp),

                        tint =
                            MaterialTheme
                                .colorScheme
                                .onPrimary
                    )
                }

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
                            "Students",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        fontWeight =
                            FontWeight.SemiBold,

                        color =
                            MaterialTheme
                                .colorScheme
                                .onPrimaryContainer
                    )

                    Spacer(
                        modifier =
                            Modifier.height(2.dp)
                    )

                    Text(
                        text =
                            if (studentCount == 1) {
                                "1 registered student"
                            } else {
                                "$studentCount registered students"
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
                }

                Icon(
                    imageVector =
                        Icons.Default.ArrowForward,

                    contentDescription =
                        "Open students",

                    tint =
                        MaterialTheme
                            .colorScheme
                            .onPrimaryContainer
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        Text(
            text = "Quick actions",

            style =
                MaterialTheme
                    .typography
                    .titleMedium,

            fontWeight =
                FontWeight.SemiBold
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        QuickActionCard(
            title = "Students",
            description = "View and manage students",
            icon = Icons.Default.Groups,
            onClick = onOpenStudents
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        QuickActionCard(
            title = "Add student",
            description = "Register a new student",
            icon = Icons.Default.PersonAdd,
            onClick = onOpenStudents
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // TODAY
        // =====================================================

        Text(
            text = "Today",

            style =
                MaterialTheme
                    .typography
                    .titleMedium,

            fontWeight =
                FontWeight.SemiBold
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        TodayCard(
            title = "Attendance",
            description =
                "Fingerprint-based attendance",
            icon =
                Icons.Default.Fingerprint
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        TodayCard(
            title = "Training schedule",
            description =
                "Manage today's driving sessions",
            icon =
                Icons.Default.Schedule
        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // =====================================================
        // UPCOMING FEATURES
        // =====================================================

        Text(
            text = "Coming next",

            style =
                MaterialTheme
                    .typography
                    .titleMedium,

            fontWeight =
                FontWeight.SemiBold
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        FeatureCard(
            title = "Attendance management",
            description =
                "Track student attendance using fingerprint authentication.",

            icon =
                Icons.Default.Fingerprint
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        FeatureCard(
            title = "Training schedule",
            description =
                "Organize upcoming student training sessions.",

            icon =
                Icons.Default.CalendarMonth
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )
    }
}


// =============================================================
// QUICK ACTION
// =============================================================

@Composable
private fun QuickActionCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },

        shape =
            RoundedCornerShape(18.dp),

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

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Surface(
                modifier =
                    Modifier.size(46.dp),

                shape =
                    RoundedCornerShape(14.dp),

                color =
                    MaterialTheme
                        .colorScheme
                        .secondaryContainer
            ) {

                Icon(
                    imageVector = icon,

                    contentDescription = null,

                    modifier =
                        Modifier.padding(12.dp),

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
                    text = title,

                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,

                    fontWeight =
                        FontWeight.SemiBold
                )

                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )

                Text(
                    text = description,

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

            Icon(
                imageVector =
                    Icons.Default.ArrowForward,

                contentDescription =
                    "Open",

                tint =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}


// =============================================================
// TODAY CARD
// =============================================================

@Composable
private fun TodayCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

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
                        .surfaceContainer
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
                    .padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Surface(
                modifier =
                    Modifier.size(46.dp),

                shape =
                    RoundedCornerShape(14.dp),

                color =
                    MaterialTheme
                        .colorScheme
                        .tertiaryContainer
            ) {

                Icon(
                    imageVector = icon,

                    contentDescription = null,

                    modifier =
                        Modifier.padding(12.dp),

                    tint =
                        MaterialTheme
                            .colorScheme
                            .onTertiaryContainer
                )
            }

            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )

            Column {

                Text(
                    text = title,

                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,

                    fontWeight =
                        FontWeight.SemiBold
                )

                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )

                Text(
                    text = description,

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


// =============================================================
// FEATURE CARD
// =============================================================

@Composable
private fun FeatureCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

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
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,

                contentDescription = null,

                modifier =
                    Modifier.size(28.dp),

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
                    text = title,

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
                    text = description,

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


// =============================================================
// GREETING
// =============================================================

private fun getGreeting(): String {

    val hour =
        java.util.Calendar
            .getInstance()
            .get(java.util.Calendar.HOUR_OF_DAY)

    return when {

        hour < 12 ->
            "Good morning"

        hour < 17 ->
            "Good afternoon"

        else ->
            "Good evening"
    }
}