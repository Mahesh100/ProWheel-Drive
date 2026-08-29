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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StudentsScreen(

    students:
    List<Student>,

    modifier:
    Modifier = Modifier,

    onAddStudent:
        () -> Unit,

    onStudentClick:
        (Student) -> Unit,

    onDeleteStudent:
        (Student) -> Unit
) {

    Column(

        modifier =
            modifier
                .fillMaxSize()
                .padding(20.dp)
    ) {

        Row(

            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column {

                Text(

                    text =
                        "Registered Students",

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,

                    fontWeight =
                        FontWeight.Bold
                )


                Text(

                    text =
                        "${students.size} student(s)"
                )
            }


            Button(
                onClick =
                    onAddStudent
            ) {

                Text(
                    "+ ADD"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        if (students.isEmpty()) {

            Card(

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Column(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(28.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        text =
                            "No students registered",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        fontWeight =
                            FontWeight.Bold
                    )


                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )


                    Text(

                        text =
                            "Add your first student to get started."
                    )


                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )


                    Button(

                        onClick =
                            onAddStudent
                    ) {

                        Text(
                            "ADD FIRST STUDENT"
                        )
                    }
                }
            }

        } else {

            LazyColumn(

                modifier =
                    Modifier.fillMaxSize(),

                verticalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {

                items(

                    items =
                        students,

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

                            onDeleteStudent(
                                student
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StudentCard(

    student:
    Student,

    onClick:
        () -> Unit,

    onDelete:
        () -> Unit
) {

    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },

        elevation =
            CardDefaults
                .cardElevation(
                    defaultElevation =
                        3.dp
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
                        Modifier.size(48.dp),

                    shape =
                        MaterialTheme
                            .shapes
                            .large,

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

                            text =
                                student.name
                                    .firstOrNull()
                                    ?.uppercase()
                                    ?: "S",

                            fontWeight =
                                FontWeight.Bold
                        )
                    }
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
                            student.name,

                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,

                        fontWeight =
                            FontWeight.Bold
                    )


                    Text(

                        text =
                            student.course,

                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            StudentInfoRow(

                label =
                    "Mobile",

                value =
                    student.mobile
            )


            StudentInfoRow(

                label =
                    "Admission",

                value =
                    student.admissionDate
            )


            StudentInfoRow(

                label =
                    "Training",

                value =
                    student.trainingTime
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text =
                        "Tap to view details",

                    style =
                        MaterialTheme
                            .typography
                            .labelMedium
                )


                TextButton(
                    onClick =
                        onDelete
                ) {

                    Text(
                        "DELETE"
                    )
                }
            }
        }
    }
}

@Composable
fun StudentInfoRow(

    label:
    String,

    value:
    String
) {

    Row(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 3.dp
                )
    ) {

        Text(

            text =
                "$label:",

            modifier =
                Modifier.width(90.dp),

            fontWeight =
                FontWeight.SemiBold
        )


        Text(
            text =
                value
        )
    }
}
