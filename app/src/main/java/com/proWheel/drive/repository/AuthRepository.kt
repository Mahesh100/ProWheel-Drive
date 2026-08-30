package com.proWheel.drive.repository

import com.proWheel.drive.data.AppDatabase
import com.proWheel.drive.data.AppUser
import com.proWheel.drive.utils.passwordUtils.PasswordUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val database: AppDatabase
) {

    suspend fun login(
        username: String,
        password: String
    ): AppUser? {

        val passwordHash =
            withContext(Dispatchers.Default) {
                PasswordUtils.hashPassword(password)
            }

        return withContext(Dispatchers.IO) {
            database
                .userDao()
                .login(
                    username.trim(),
                    passwordHash
                )
        }
    }

    suspend fun findUser(
        username: String
    ): AppUser? {

        return withContext(Dispatchers.IO) {
            database
                .userDao()
                .getUserByUsername(
                    username.trim()
                )
        }
    }

    suspend fun register(
        username: String,
        password: String,
        mobile: String
    ) {

        val passwordHash =
            withContext(Dispatchers.Default) {
                PasswordUtils.hashPassword(password)
            }

        val user =
            AppUser(
                username = username.trim(),
                passwordHash = passwordHash,
                mobile = mobile.trim()
            )

        withContext(Dispatchers.IO) {
            database
                .userDao()
                .insertUser(user)
        }
    }
}