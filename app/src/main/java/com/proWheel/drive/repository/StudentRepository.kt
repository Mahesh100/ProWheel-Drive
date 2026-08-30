package com.proWheel.drive.repository

import com.proWheel.drive.data.Student
import com.proWheel.drive.data.StudentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepository(
    private val studentDao: StudentDao
) {

    suspend fun getAllStudents(): List<Student> {
        return withContext(Dispatchers.IO) {
            studentDao.getAllStudents()
        }
    }

    suspend fun getStudentById(
        studentId: Int
    ): Student? {
        return withContext(Dispatchers.IO) {
            studentDao.getStudentById(studentId)
        }
    }

    suspend fun insertStudent(
        student: Student
    ): Long {
        return withContext(Dispatchers.IO) {
            studentDao.insertStudent(student)
        }
    }

    suspend fun deleteStudent(
        studentId: Int
    ) {
        withContext(Dispatchers.IO) {
            studentDao.deleteStudent(studentId)
        }
    }
}