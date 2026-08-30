package com.proWheel.drive.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.proWheel.drive.data.Student


@Composable
fun StudentsScreen(
    students: List<Student>,
    modifier: Modifier = Modifier,
    onAddStudent: () -> Unit,
    onStudentClick: (Student) -> Unit,
    onDeleteStudent: (Student) -> Unit
) {

    var studentToDelete by remember {
        mutableStateOf<Student?>(null)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            )
    ) {

        // =====================================================
        // HEADER
        // =====================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Students",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = studentCountText(students.size),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


            FilledTonalButton(
                onClick = onAddStudent,
                shape = RoundedCornerShape(14.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = "Add student",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // =====================================================
        // EMPTY STATE
        // =====================================================

        if (students.isEmpty()) {

            EmptyStudentsState(
                onAddStudent = onAddStudent
            )

        } else {

            // =================================================
            // STUDENT LIST
            // =================================================

            LazyColumn(
                modifier = Modifier.fillMaxSize(),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = students,
                    key = { student ->
                        student.id
                    }
                ) { student ->

                    StudentCard(
                        student = student,

                        onClick = {
                            onStudentClick(student)
                        },

                        onDelete = {
                            studentToDelete = student
                        }
                    )
                }
            }
        }
    }


    // =========================================================
    // DELETE CONFIRMATION
    // =========================================================

    studentToDelete?.let { student ->

        AlertDialog(

            onDismissRequest = {
                studentToDelete = null
            },

            icon = {

                Icon(
                    imageVector =
                        Icons.Default.DeleteOutline,

                    contentDescription = null,

                    tint =
                        MaterialTheme
                            .colorScheme
                            .error
                )
            },

            title = {

                Text(
                    text = "Delete student?"
                )
            },

            text = {

                Text(
                    text =
                        "Are you sure you want to delete ${student.name}? This action cannot be undone."
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        onDeleteStudent(student)

                        studentToDelete = null
                    }
                ) {

                    Text(
                        text = "Delete",

                        color =
                            MaterialTheme
                                .colorScheme
                                .error
                    )
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        studentToDelete = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}


// =============================================================
// STUDENT CARD
// =============================================================

@Composable
private fun StudentCard(
    student: Student,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {

    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },

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

            // =================================================
            // STUDENT HEADER
            // =================================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                StudentAvatar(
                    name = student.name
                )


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
                            student.name.ifBlank {
                                "Unnamed student"
                            },

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        fontWeight =
                            FontWeight.SemiBold,

                        maxLines = 1,

                        overflow =
                            TextOverflow.Ellipsis
                    )


                    Spacer(
                        modifier =
                            Modifier.height(3.dp)
                    )


                    if (student.course.isNotBlank()) {

                        Text(
                            text =
                                student.course,

                            style =
                                MaterialTheme
                                    .typography
                                    .bodyMedium,

                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant,

                            maxLines = 1,

                            overflow =
                                TextOverflow.Ellipsis
                        )
                    }
                }


                IconButton(
                    onClick = onDelete
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.DeleteOutline,

                        contentDescription =
                            "Delete ${student.name}",

                        tint =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            HorizontalDivider(
                color =
                    MaterialTheme
                        .colorScheme
                        .outlineVariant
            )


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            // =================================================
            // INFORMATION
            // =================================================

            StudentInfoItem(
                icon =
                    Icons.Default.Phone,

                label =
                    "Mobile",

                value =
                    student.mobile
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            StudentInfoItem(
                icon =
                    Icons.Default.CalendarMonth,

                label =
                    "Admission",

                value =
                    student.admissionDate
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            StudentInfoItem(
                icon =
                    Icons.Default.Schedule,

                label =
                    "Training",

                value =
                    student.trainingTime
            )


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            // =================================================
            // FOOTER
            // =================================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text =
                        "View student details",

                    modifier =
                        Modifier.weight(1f),

                    style =
                        MaterialTheme
                            .typography
                            .labelLarge,

                    color =
                        MaterialTheme
                            .colorScheme
                            .primary
                )


                Icon(
                    imageVector =
                        Icons.Default.ArrowForward,

                    contentDescription =
                        "View details",

                    modifier =
                        Modifier.size(20.dp),

                    tint =
                        MaterialTheme
                            .colorScheme
                            .primary
                )
            }
        }
    }
}


// =============================================================
// STUDENT AVATAR
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
            Modifier.size(52.dp),

        shape =
            CircleShape,

        color =
            MaterialTheme
                .colorScheme
                .primaryContainer
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
                text = initial,

                style =
                    MaterialTheme
                        .typography
                        .titleMedium,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme
                        .colorScheme
                        .onPrimaryContainer
            )
        }
    }
}


// =============================================================
// STUDENT INFORMATION ITEM
// =============================================================

@Composable
private fun StudentInfoItem(
    icon:
    androidx.compose.ui.graphics.vector.ImageVector,

    label: String,

    value: String
) {

    Row(

        modifier =
            Modifier.fillMaxWidth(),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Icon(

            imageVector = icon,

            contentDescription = null,

            modifier =
                Modifier.size(20.dp),

            tint =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )


        Spacer(
            modifier =
                Modifier.width(12.dp)
        )


        Column(
            modifier =
                Modifier.weight(1f)
        ) {

            Text(

                text = label,

                style =
                    MaterialTheme
                        .typography
                        .labelSmall,

                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )


            Text(

                text =
                    value.ifBlank {
                        "Not provided"
                    },

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,

                maxLines = 1,

                overflow =
                    TextOverflow.Ellipsis
            )
        }
    }
}


// =============================================================
// EMPTY STATE
// =============================================================

@Composable
private fun EmptyStudentsState(
    onAddStudent: () -> Unit
) {

    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp
                ),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

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
                    Icons.Default.Groups,

                contentDescription = null,

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
                Modifier.height(20.dp)
        )


        Text(
            text =
                "No students yet",

            style =
                MaterialTheme
                    .typography
                    .titleLarge,

            fontWeight =
                FontWeight.SemiBold
        )


        Spacer(
            modifier =
                Modifier.height(6.dp)
        )


        Text(
            text =
                "Add your first student to start managing training, attendance and admission details.",

            modifier =
                Modifier.fillMaxWidth(),

            style =
                MaterialTheme
                    .typography
                    .bodyMedium,

            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant,

            textAlign =
                androidx.compose.ui.text.style.TextAlign.Center
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        FilledTonalButton(
            onClick =
                onAddStudent,

            shape =
                RoundedCornerShape(14.dp)
        ) {

            Icon(
                imageVector =
                    Icons.Default.Add,

                contentDescription = null
            )

            Spacer(
                modifier =
                    Modifier.width(8.dp)
            )

            Text(
                text = "Add first student"
            )
        }
    }
}


// =============================================================
// STUDENT COUNT
// =============================================================

private fun studentCountText(
    count: Int
): String {

    return when (count) {

        0 ->
            "No students registered"

        1 ->
            "1 student registered"

        else ->
            "$count students registered"
    }
}