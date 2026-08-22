package com.proWheel.drive.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_users",
    indices = [
        Index(value = ["username"], unique = true)
    ]
)
data class AppUser(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val username: String,

    // We will store a password hash, NOT the plain password.
    val passwordHash: String,

    val mobile: String
)