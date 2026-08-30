package com.proWheel.drive.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proWheel.drive.data.Student
import com.proWheel.drive.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    private val _students =
        MutableStateFlow<List<Student>>(
            emptyList()
        )

    val students: StateFlow<List<Student>> =
        _students.asStateFlow()


    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()


    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?> =
        _error.asStateFlow()


    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    fun loadStudents() {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                _students.value =
                    repository.getAllStudents()

            } catch (e: Exception) {

                _error.value =
                    e.message
                        ?: "Unable to load students"

            } finally {

                _isLoading.value = false
            }
        }
    }


    // =========================================================
    // ADD STUDENT
    // =========================================================

    fun addStudent(
        student: Student,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                repository.insertStudent(
                    student
                )

                loadStudents()

                onSuccess()

            } catch (e: Exception) {

                val message =
                    e.message
                        ?: "Unable to save student"

                _error.value =
                    message

                onError(message)

            } finally {

                _isLoading.value = false
            }
        }
    }


    // =========================================================
    // DELETE STUDENT
    // =========================================================

    fun deleteStudent(
        studentId: Int,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                repository.deleteStudent(
                    studentId
                )

                loadStudents()

                onSuccess()

            } catch (e: Exception) {

                val message =
                    e.message
                        ?: "Unable to delete student"

                _error.value =
                    message

                onError(message)

            } finally {

                _isLoading.value = false
            }
        }
    }


    // =========================================================
    // GET STUDENT
    // =========================================================

    fun getStudentById(
        studentId: Int,
        onResult: (Student?) -> Unit
    ) {

        viewModelScope.launch {

            try {

                val student =
                    repository.getStudentById(
                        studentId
                    )

                onResult(student)

            } catch (e: Exception) {

                _error.value =
                    e.message
                        ?: "Unable to load student"

                onResult(null)
            }
        }
    }


    // =========================================================
    // CLEAR ERROR
    // =========================================================

    fun clearError() {

        _error.value = null
    }
}