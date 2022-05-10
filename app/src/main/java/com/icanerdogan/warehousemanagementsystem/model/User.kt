package com.icanerdogan.warehousemanagementsystem.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    val userID: Int?,

    @ColumnInfo(name = "firstname")
    val userFirstName: String?,

    @ColumnInfo(name = "lastname")
    val userLastName: String?,

    @ColumnInfo(name = "username")
    val userName: String?,

    @ColumnInfo(name = "password")
    val userPassword: String?,
)