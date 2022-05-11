package com.icanerdogan.warehousemanagementsystem.service.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.model.User

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(vararg user: User)

    @Query("SELECT * FROM User WHERE username LIKE :username")
    suspend fun findUsername(username: String?): List<User>

    @Query("SELECT * FROM User WHERE username LIKE :username AND password LIKE :password")
    suspend fun getUser(username: String, password: String): User?
}