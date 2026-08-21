package com.proWheel.drive.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val admissionDate: String,

    val trainingTime: String,

    val name: String,

    val course: String,

    val services: String,

    val address: String,

    val mobile: String,

    val totalFees: String
)