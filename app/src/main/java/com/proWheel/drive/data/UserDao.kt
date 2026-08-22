package com.proWheel.drive.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: AppUser): Long

    @Query("SELECT * FROM app_users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): AppUser?

    @Query(
        "SELECT * FROM app_users " +
                "WHERE username = :username AND passwordHash = :passwordHash " +
                "LIMIT 1"
    )
    suspend fun login(
        username: String,
        passwordHash: String
    ): AppUser?
}