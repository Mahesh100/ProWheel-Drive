package com.proWheel.drive.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proWheel.drive.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authState =
        MutableStateFlow<AuthState>(
            AuthState.Idle
        )

    val authState: StateFlow<AuthState> =
        _authState.asStateFlow()


    // =========================================================
    // LOGIN
    // =========================================================

    fun login(
        username: String,
        password: String,
        keepLoggedIn: Boolean
    ) {

        if (username.isBlank()) {

            _authState.value =
                AuthState.Error(
                    "Please enter username"
                )

            return
        }

        if (password.isBlank()) {

            _authState.value =
                AuthState.Error(
                    "Please enter password"
                )

            return
        }


        viewModelScope.launch {

            _authState.value =
                AuthState.Loading

            try {

                val user =
                    repository.login(
                        username = username,
                        password = password
                    )


                if (user != null) {

                    _authState.value =
                        AuthState.LoginSuccess(
                            username =
                                user.username,

                            keepLoggedIn =
                                keepLoggedIn
                        )

                } else {

                    _authState.value =
                        AuthState.Error(
                            "Invalid username or password"
                        )
                }

            } catch (e: Exception) {

                _authState.value =
                    AuthState.Error(
                        e.message
                            ?: "Login failed"
                    )
            }
        }
    }


    // =========================================================
    // REGISTER
    // =========================================================

    fun register(
        username: String,
        password: String,
        confirmPassword: String,
        mobile: String
    ) {

        val cleanUsername =
            username.trim()

        val cleanMobile =
            mobile.trim()


        if (cleanUsername.isBlank()) {

            _authState.value =
                AuthState.Error(
                    "Please enter username"
                )

            return
        }


        if (password.isBlank()) {

            _authState.value =
                AuthState.Error(
                    "Please enter password"
                )

            return
        }


        if (password != confirmPassword) {

            _authState.value =
                AuthState.Error(
                    "Passwords do not match"
                )

            return
        }


        if (
            cleanMobile.length != 10 ||
            !cleanMobile.all { it.isDigit() }
        ) {

            _authState.value =
                AuthState.Error(
                    "Please enter a valid 10-digit mobile number"
                )

            return
        }


        viewModelScope.launch {

            _authState.value =
                AuthState.Loading

            try {

                val existingUser =
                    repository.findUser(
                        cleanUsername
                    )


                if (existingUser != null) {

                    _authState.value =
                        AuthState.Error(
                            "Username already exists"
                        )

                    return@launch
                }


                repository.register(
                    username =
                        cleanUsername,

                    password =
                        password,

                    mobile =
                        cleanMobile
                )


                _authState.value =
                    AuthState.RegisterSuccess

            } catch (e: Exception) {

                _authState.value =
                    AuthState.Error(
                        e.message
                            ?: "Registration failed"
                    )
            }
        }
    }


    // =========================================================
    // RESET
    // =========================================================

    fun resetState() {

        _authState.value =
            AuthState.Idle
    }
}


// =============================================================
// AUTH STATE
// =============================================================

sealed interface AuthState {

    data object Idle : AuthState

    data object Loading : AuthState

    data class LoginSuccess(
        val username: String,
        val keepLoggedIn: Boolean
    ) : AuthState

    data object RegisterSuccess :
        AuthState

    data class Error(
        val message: String
    ) : AuthState
}