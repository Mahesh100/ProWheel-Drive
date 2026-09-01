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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.draw.clip
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

    var searchQuery by remember {
        mutableStateOf("")
    }

    var studentToDelete by remember {
        mutableStateOf<Student?>(null)
    }


    val filteredStudents =
        remember(
            students,
            searchQuery
        ) {

            if (searchQuery.isBlank()) {

                students

            } else {

                val query =
                    searchQuery.trim()
                        .lowercase()

                students.filter { student ->

                    student.name
                        .lowercase()
                        .contains(query) ||

                            student.mobile
                                .lowercase()
                                .contains(query) ||

                            student.course
                                .lowercase()
                                .contains(query)
                }
            }
        }


    Box(
        modifier =
            modifier.fillMaxSize()
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 20.dp
                    )
        ) {

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            // =====================================================
            // HEADER
            // =====================================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

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
                                .headlineSmall,
                        fontWeight =
                            FontWeight.Bold
                    )


                    Spacer(
                        modifier =
                            Modifier.height(3.dp)
                    )


                    Text(
                        text =
                            "${students.size} registered student" +
                                    if (students.size == 1)
                                        ""
                                    else
                                        "s",
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }


                // =================================================
                // ADD BUTTON
                // =================================================

                Button(
                    onClick =
                        onAddStudent
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Add,
                        contentDescription =
                            null
                    )

                    Spacer(
                        modifier =
                            Modifier.width(6.dp)
                    )

                    Text(
                        text =
                            "Add Student"
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            // =====================================================
            // SEARCH
            // =====================================================

            OutlinedTextField(

                value =
                    searchQuery,

                onValueChange = {
                    searchQuery = it
                },

                modifier =
                    Modifier.fillMaxWidth(),

                singleLine = true,

                leadingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.Search,
                        contentDescription =
                            "Search students"
                    )
                },

                placeholder = {

                    Text(
                        text =
                            "Search by name, mobile or course"
                    )
                },

                shape =
                    MaterialTheme
                        .shapes
                        .large
            )


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            // =====================================================
            // CONTENT
            // =====================================================

            when {

                // -------------------------------------------------
                // NO STUDENTS
                // -------------------------------------------------

                students.isEmpty() -> {

                    EmptyStudentsState(
                        onAddStudent =
                            onAddStudent
                    )
                }


                // -------------------------------------------------
                // NO SEARCH RESULT
                // -------------------------------------------------

                filteredStudents.isEmpty() -> {

                    NoSearchResultState(
                        searchQuery =
                            searchQuery,

                        onClearSearch = {
                            searchQuery = ""
                        }
                    )
                }


                // -------------------------------------------------
                // STUDENT LIST
                // -------------------------------------------------

                else -> {

                    LazyColumn(

                        modifier =
                            Modifier.fillMaxSize(),

                        verticalArrangement =
                            Arrangement.spacedBy(
                                12.dp
                            ),

                        contentPadding =
                            androidx.compose.foundation.layout
                                .PaddingValues(
                                    bottom = 96.dp
                                )
                    ) {

                        items(

                            items =
                                filteredStudents,

                            key = {
                                it.id
                            }
                        ) { student ->

                            StudentCard(

                                student =
                                    student,

                                onClick = {

                                    onStudentClick(
                                        student
                                    )
                                },

                                onDelete = {

                                    studentToDelete =
                                        student
                                }
                            )
                        }
                    }
                }
            }
        }


        // =========================================================
        // FLOATING ACTION BUTTON
        // =========================================================

        if (students.isNotEmpty()) {

            FloatingActionButton(

                onClick =
                    onAddStudent,

                modifier =
                    Modifier
                        .align(
                            Alignment.BottomEnd
                        )
                        .padding(
                            end = 20.dp,
                            bottom = 20.dp
                        )
            ) {

                Icon(
                    imageVector =
                        Icons.Default.Add,
                    contentDescription =
                        "Add student"
                )
            }
        }
    }


    // =============================================================
    // DELETE CONFIRMATION
    // =============================================================

    studentToDelete?.let { student ->

        AlertDialog(

            onDismissRequest = {

                studentToDelete =
                    null
            },

            icon = {

                Icon(
                    imageVector =
                        Icons.Default.Delete,
                    contentDescription =
                        null
                )
            },

            title = {

                Text(
                    text =
                        "Delete student?"
                )
            },

            text = {

                Text(
                    text =
                        "Are you sure you want to delete " +
                                "${student.name}? This action cannot be undone."
                )
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        onDeleteStudent(
                            student
                        )

                        studentToDelete =
                            null
                    }
                ) {

                    Text(
                        text =
                            "Delete"
                    )
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {

                        studentToDelete =
                            null
                    }
                ) {

                    Text(
                        text =
                            "Cancel"
                    )
                }
            }
        )
    }
}


// =================================================================
// STUDENT CARD
// =================================================================

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
                .clickable(
                    onClick = onClick
                ),

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

        Column(
            modifier =
                Modifier.padding(
                    16.dp
                )
        ) {

            // =====================================================
            // TOP ROW
            // =====================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                StudentAvatar(
                    name =
                        student.name
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


                    Text(

                        text =
                            student.course.ifBlank {
                                "Course not specified"
                            },

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


                // =================================================
                // DELETE
                // =================================================

                IconButton(

                    onClick =
                        onDelete
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Delete,

                        contentDescription =
                            "Delete ${student.name}",

                        tint =
                            MaterialTheme
                                .colorScheme
                                .error
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            // =====================================================
            // DIVIDER
            // =====================================================

            Surface(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp),

                color =
                    MaterialTheme
                        .colorScheme
                        .outlineVariant
            ) {}


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            // =====================================================
            // DETAILS
            // =====================================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {

                StudentDetailItem(

                    modifier =
                        Modifier.weight(1f),

                    icon =
                        Icons.Default.Phone,

                    label =
                        "Mobile",

                    value =
                        student.mobile
                            .ifBlank {
                                "Not available"
                            }
                )


                StudentDetailItem(

                    modifier =
                        Modifier.weight(1f),

                    icon =
                        Icons.Default.School,

                    label =
                        "Training",

                    value =
                        student.trainingTime
                            .ifBlank {
                                "Not scheduled"
                            }
                )
            }


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            // =====================================================
            // ADMISSION DATE
            // =====================================================

            Text(

                text =
                    "Admission: ${
                        student.admissionDate
                            .ifBlank {
                                "Not available"
                            }
                    }",

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
}


// =================================================================
// STUDENT AVATAR
// =================================================================

@Composable
private fun StudentAvatar(
    name: String
) {

    val initial =
        name
            .trim()
            .firstOrNull()
            ?.uppercaseChar()
            ?.toString()
            ?: "?"


    Surface(

        modifier =
            Modifier
                .size(52.dp)
                .clip(
                    CircleShape
                ),

        shape =
            CircleShape,

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

                text =
                    initial,

                style =
                    MaterialTheme
                        .typography
                        .titleLarge,

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


// =================================================================
// STUDENT DETAIL ITEM
// =================================================================

@Composable
private fun StudentDetailItem(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {

    Row(

        modifier =
            modifier,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Icon(

            imageVector =
                icon,

            contentDescription =
                null,

            modifier =
                Modifier.size(18.dp),

            tint =
                MaterialTheme
                    .colorScheme
                    .primary
        )


        Spacer(
            modifier =
                Modifier.width(8.dp)
        )


        Column {

            Text(

                text =
                    label,

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
                    value,

                style =
                    MaterialTheme
                        .typography
                        .bodySmall,

                maxLines = 1,

                overflow =
                    TextOverflow.Ellipsis
            )
        }
    }
}


// =================================================================
// EMPTY STATE
// =================================================================

@Composable
private fun EmptyStudentsState(
    onAddStudent: () -> Unit
) {

    Box(
        modifier =
            Modifier.fillMaxSize(),
        contentAlignment =
            Alignment.Center
    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 32.dp
                    ),

            horizontalAlignment =
                Alignment.CenterHorizontally
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

                Box(
                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Person,

                        contentDescription =
                            null,

                        modifier =
                            Modifier.size(36.dp),

                        tint =
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
                    "No students yet",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )


            Text(

                text =
                    "Add your first student to start managing training and attendance.",

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
                    Modifier.height(20.dp)
            )


            Button(
                onClick =
                    onAddStudent
            ) {

                Icon(
                    imageVector =
                        Icons.Default.Add,
                    contentDescription =
                        null
                )

                Spacer(
                    modifier =
                        Modifier.width(8.dp)
                )

                Text(
                    text =
                        "Add Student"
                )
            }
        }
    }
}


// =================================================================
// SEARCH EMPTY STATE
// =================================================================

@Composable
private fun NoSearchResultState(
    searchQuery: String,
    onClearSearch: () -> Unit
) {

    Box(
        modifier =
            Modifier.fillMaxSize(),
        contentAlignment =
            Alignment.Center
    ) {

        Column(

            horizontalAlignment =
                Alignment.CenterHorizontally,

            modifier =
                Modifier.padding(
                    horizontal = 32.dp
                )
        ) {

            Icon(

                imageVector =
                    Icons.Default.Search,

                contentDescription =
                    null,

                modifier =
                    Modifier.size(48.dp),

                tint =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )


            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            Text(

                text =
                    "No students found",

                style =
                    MaterialTheme
                        .typography
                        .titleMedium,

                fontWeight =
                    FontWeight.SemiBold
            )


            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )


            Text(

                text =
                    "No students match \"$searchQuery\".",

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
                    Modifier.height(12.dp)
            )


            TextButton(
                onClick =
                    onClearSearch
            ) {

                Text(
                    text =
                        "Clear search"
                )
            }
        }
    }
}