package com.icanerdogan.warehousemanagementsystem.viewmodel.user

import android.app.Application
import androidx.lifecycle.LiveData
import com.icanerdogan.warehousemanagementsystem.model.User
import com.icanerdogan.warehousemanagementsystem.service.user.UserDatabase
import com.icanerdogan.warehousemanagementsystem.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel(application: Application) : BaseViewModel(application) {

    suspend fun controlData(username: String, password: String): Boolean{

        return UserDatabase(getApplication()).userDao().getUser(username, password) != null
    }

    fun fieldControl(
        editTextUserName: String,
        editTextPassword: String
    ): Boolean {
        if (editTextUserName.isEmpty() ||
            editTextPassword.isEmpty()
        ) {
            return true
        }
        return false
    }
}