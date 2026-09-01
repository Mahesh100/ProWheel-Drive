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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
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
                vertical = 16.dp
            )
    ) {

        // =====================================================
        // HEADER
        // =====================================================

        Text(
            text = "Good day, $username 👋",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Manage your driving school with ease.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // =====================================================
        // STUDENT OVERVIEW
        // =====================================================

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.extraLarge
                )
                .clickable {
                    onOpenStudents()
                },
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "Students",
                            style =
                                MaterialTheme.typography.titleMedium,
                            fontWeight =
                                FontWeight.SemiBold,
                            color =
                                MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = "Total registered students",
                            style =
                                MaterialTheme.typography.bodyMedium,
                            color =
                                MaterialTheme.colorScheme
                                    .onPrimaryContainer
                                    .copy(alpha = 0.75f)
                        )
                    }


                    Surface(
                        modifier = Modifier.size(56.dp),
                        shape = MaterialTheme.shapes.large,
                        color =
                            MaterialTheme.colorScheme.primary
                    ) {

                        Box(
                            contentAlignment =
                                Alignment.Center
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.People,
                                contentDescription =
                                    "Students",
                                tint =
                                    MaterialTheme.colorScheme
                                        .onPrimary
                            )
                        }
                    }
                }


                Spacer(
                    modifier = Modifier.height(18.dp)
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment =
                        Alignment.Bottom
                ) {

                    Text(
                        text = studentCount.toString(),
                        style =
                            MaterialTheme.typography.displaySmall,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme
                                .onPrimaryContainer
                    )


                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )


                    Text(
                        text = "registered",
                        modifier =
                            Modifier.padding(
                                bottom = 7.dp
                            ),
                        style =
                            MaterialTheme.typography.bodyMedium,
                        color =
                            MaterialTheme.colorScheme
                                .onPrimaryContainer
                                .copy(alpha = 0.75f)
                    )
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 4.dp
                        ),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "View all students",
                        modifier = Modifier.weight(1f),
                        style =
                            MaterialTheme.typography.labelLarge,
                        fontWeight =
                            FontWeight.SemiBold,
                        color =
                            MaterialTheme.colorScheme
                                .onPrimaryContainer
                    )

                    Icon(
                        imageVector =
                            Icons.Default.ArrowForward,
                        contentDescription =
                            "View students",
                        tint =
                            MaterialTheme.colorScheme
                                .onPrimaryContainer
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // =====================================================
        // QUICK ACTIONS
        // =====================================================

        Text(
            text = "Quick actions",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            DashboardActionCard(
                modifier =
                    Modifier.weight(1f),
                icon =
                    Icons.Default.PersonAdd,
                title =
                    "Students",
                subtitle =
                    "Manage",
                onClick =
                    onOpenStudents
            )


            DashboardActionCard(
                modifier =
                    Modifier.weight(1f),
                icon =
                    Icons.Default.CalendarToday,
                title =
                    "Attendance",
                subtitle =
                    "Coming soon",
                onClick = {}
            )
        }


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // =====================================================
        // TODAY'S TRAINING
        // =====================================================

        Text(
            text = "Today's training",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surfaceContainerLow
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = MaterialTheme.shapes.medium,
                    color =
                        MaterialTheme.colorScheme
                            .secondaryContainer
                ) {

                    Box(
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.DirectionsCar,
                            contentDescription =
                                "Driving training",
                            tint =
                                MaterialTheme.colorScheme
                                    .onSecondaryContainer
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.width(14.dp)
                )


                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Training schedule",
                        style =
                            MaterialTheme.typography.titleSmall,
                        fontWeight =
                            FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text =
                            "Training schedule management will be available soon.",
                        style =
                            MaterialTheme.typography.bodySmall,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // =====================================================
        // FINGERPRINT ATTENDANCE
        // =====================================================

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surfaceContainerLow
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Fingerprint attendance",
                    style =
                        MaterialTheme.typography.titleSmall,
                    fontWeight =
                        FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text =
                        "Biometric attendance will be integrated with the student management system.",
                    style =
                        MaterialTheme.typography.bodyMedium,
                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )
            }
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}


// =============================================================
// QUICK ACTION CARD
// =============================================================

@Composable
private fun DashboardActionCard(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .height(118.dp)
            .clickable(
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement =
                Arrangement.SpaceBetween
        ) {

            Surface(
                modifier = Modifier.size(40.dp),
                shape = MaterialTheme.shapes.medium,
                color =
                    MaterialTheme.colorScheme
                        .secondaryContainer
            ) {

                Box(
                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint =
                            MaterialTheme.colorScheme
                                .onSecondaryContainer
                    )
                }
            }


            Column {

                Text(
                    text = title,
                    style =
                        MaterialTheme.typography.titleSmall,
                    fontWeight =
                        FontWeight.SemiBold
                )

                Text(
                    text = subtitle,
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )
            }
        }
    }
}