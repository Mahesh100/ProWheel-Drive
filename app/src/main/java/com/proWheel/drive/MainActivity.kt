package com.proWheel.drive

import android.content.Context
import android.os.Bundle
import android.widget.Toast

import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.proWheel.drive.data.AppDatabase
import com.proWheel.drive.repository.AuthRepository
import com.proWheel.drive.repository.StudentRepository

import com.proWheel.drive.ui.screens.LoginScreen
import com.proWheel.drive.ui.screens.MainApplicationScreen
import com.proWheel.drive.ui.screens.RegisterScreen

import com.proWheel.drive.ui.theme.FingerPrint3Theme

import com.proWheel.drive.ui.viewmodel.AuthState
import com.proWheel.drive.ui.viewmodel.AuthViewModel
import com.proWheel.drive.ui.viewmodel.AuthViewModelFactory
import com.proWheel.drive.ui.viewmodel.StudentViewModel
import com.proWheel.drive.ui.viewmodel.StudentViewModelFactory


class MainActivity : FragmentActivity() {

    companion object {

        private const val PREFERENCES_NAME =
            "prowheel_preferences"

        private const val KEY_IS_LOGGED_IN =
            "is_logged_in"

        private const val KEY_USERNAME =
            "username"
    }


    // =========================================================
    // VIEW MODELS
    // =========================================================

    private lateinit var authViewModel: AuthViewModel

    private lateinit var studentViewModel: StudentViewModel


    // =========================================================
    // UI STATE
    // =========================================================

    private var isLoggedIn by
    mutableStateOf(false)

    private var showRegisterScreen by
    mutableStateOf(false)

    private var loggedInUsername by
    mutableStateOf("")


    // =========================================================
    // ACTIVITY CREATED
    // =========================================================

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )


        // -----------------------------------------------------
        // Initialize database
        // -----------------------------------------------------

        val database =
            AppDatabase.getDatabase(
                applicationContext
            )


        // -----------------------------------------------------
        // Initialize AuthRepository
        // -----------------------------------------------------

        val authRepository =
            AuthRepository(
                database
            )


        // -----------------------------------------------------
        // Initialize AuthViewModel
        // -----------------------------------------------------

        authViewModel =
            ViewModelProvider(
                this,
                AuthViewModelFactory(
                    authRepository
                )
            )[AuthViewModel::class.java]


        // -----------------------------------------------------
        // Initialize StudentRepository
        // -----------------------------------------------------

        val studentRepository =
            StudentRepository(
                database.studentDao()
            )


        // -----------------------------------------------------
        // Initialize StudentViewModel
        // -----------------------------------------------------

        studentViewModel =
            ViewModelProvider(
                this,
                StudentViewModelFactory(
                    studentRepository
                )
            )[StudentViewModel::class.java]


        // -----------------------------------------------------
        // Restore login state
        // -----------------------------------------------------

        loadLoginState()


        // -----------------------------------------------------
        // Compose
        // -----------------------------------------------------

        setContent {

            FingerPrint3Theme {

                MaterialTheme {

                    ProWheelApp()
                }
            }
        }
    }


    // =========================================================
    // ROOT APPLICATION
    // =========================================================

    @Composable
    private fun ProWheelApp() {

        val authState by
        authViewModel
            .authState
            .collectAsStateWithLifecycle()


        // -----------------------------------------------------
        // Handle authentication events
        // -----------------------------------------------------

        LaunchedEffect(authState) {

            when (
                val state = authState
            ) {

                is AuthState.LoginSuccess -> {

                    handleLoginSuccess(
                        username =
                            state.username,

                        keepLoggedIn =
                            state.keepLoggedIn
                    )
                }


                AuthState.RegisterSuccess -> {

                    handleRegistrationSuccess()
                }


                is AuthState.Error -> {

                    showMessage(
                        state.message
                    )

                    authViewModel.resetState()
                }


                AuthState.Idle -> {
                    // Nothing to do.
                }


                AuthState.Loading -> {
                    // Loading is handled by the screen.
                }
            }
        }


        // -----------------------------------------------------
        // Screen selection
        // -----------------------------------------------------

        when {

            // =================================================
            // MAIN APPLICATION
            // =================================================

            isLoggedIn -> {

                MainApplicationScreen(

                    username =
                        loggedInUsername,

                    studentViewModel =
                        studentViewModel,

                    onLogout = {

                        logout()
                    }
                )
            }


            // =================================================
            // REGISTER
            // =================================================

            showRegisterScreen -> {

                RegisterScreen(

                    onRegister = {
                            username,
                            password,
                            confirmPassword,
                            mobile ->

                        authViewModel.register(

                            username =
                                username,

                            password =
                                password,

                            confirmPassword =
                                confirmPassword,

                            mobile =
                                mobile
                        )
                    },

                    onBackToLogin = {

                        showRegisterScreen =
                            false

                        authViewModel.resetState()
                    }
                )
            }


            // =================================================
            // LOGIN
            // =================================================

            else -> {

                LoginScreen(

                    onLogin = {
                            username,
                            password,
                            keepLoggedIn ->

                        authViewModel.login(

                            username =
                                username,

                            password =
                                password,

                            keepLoggedIn =
                                keepLoggedIn
                        )
                    },

                    onRegister = {

                        showRegisterScreen =
                            true

                        authViewModel.resetState()
                    }
                )
            }
        }
    }


    // =========================================================
    // LOGIN SUCCESS
    // =========================================================

    private fun handleLoginSuccess(
        username: String,
        keepLoggedIn: Boolean
    ) {

        loggedInUsername =
            username


        isLoggedIn =
            true


        showRegisterScreen =
            false


        if (keepLoggedIn) {

            saveLoginState(
                username
            )

        } else {

            clearLoginState()
        }


        authViewModel.resetState()


        showMessage(
            "Login successful"
        )
    }


    // =========================================================
    // REGISTRATION SUCCESS
    // =========================================================

    private fun handleRegistrationSuccess() {

        showRegisterScreen =
            false


        authViewModel.resetState()


        showMessage(
            "Registration successful. Please login."
        )
    }


    // =========================================================
    // LOGIN STATE
    // =========================================================

    private fun loadLoginState() {

        val preferences =
            getSharedPreferences(
                PREFERENCES_NAME,
                Context.MODE_PRIVATE
            )


        val savedLogin =
            preferences.getBoolean(
                KEY_IS_LOGGED_IN,
                false
            )


        val savedUsername =
            preferences.getString(
                KEY_USERNAME,
                ""
            ).orEmpty()


        if (
            savedLogin &&
            savedUsername.isNotBlank()
        ) {

            isLoggedIn =
                true

            loggedInUsername =
                savedUsername

        } else {

            isLoggedIn =
                false

            loggedInUsername =
                ""
        }
    }


    // =========================================================
    // SAVE LOGIN STATE
    // =========================================================

    private fun saveLoginState(
        username: String
    ) {

        getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .putBoolean(
                KEY_IS_LOGGED_IN,
                true
            )
            .putString(
                KEY_USERNAME,
                username
            )
            .apply()
    }


    // =========================================================
    // CLEAR LOGIN STATE
    // =========================================================

    private fun clearLoginState() {

        getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .clear()
            .apply()
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private fun logout() {

        clearLoginState()


        loggedInUsername =
            ""


        isLoggedIn =
            false


        showRegisterScreen =
            false


        authViewModel.resetState()


        showMessage(
            "Logged out"
        )
    }


    // =========================================================
    // MESSAGE
    // =========================================================

    private fun showMessage(
        message: String
    ) {

        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}