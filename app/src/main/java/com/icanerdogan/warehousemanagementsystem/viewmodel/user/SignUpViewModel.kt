package com.icanerdogan.warehousemanagementsystem.viewmodel.user

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.MainApplication
import com.icanerdogan.warehousemanagementsystem.model.User
import com.icanerdogan.warehousemanagementsystem.service.user.UserDatabase
import com.icanerdogan.warehousemanagementsystem.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : BaseViewModel(application) {
    var findedUsernameData = MutableLiveData<List<User>>()

    private val context: Context = MainApplication.applicationContext()

    fun addUser(user: User) {
        launch {
            UserDatabase(getApplication()).userDao().insertUser(user)
        }
    }

    fun findSameData(username: String) {
        launch {
            val finded = UserDatabase(getApplication()).userDao().findUsername(username)
            showSame(usernameList = finded)
        }
    }

    private fun showSame(usernameList: List<User>) {
        findedUsernameData.value = usernameList
    }

    fun createUser(
        userFirstName: String,
        userLastName: String,
        userUsername: String,
        userPassword: String
    ): User {
        return User(
            userFirstName = userFirstName,
            userLastName = userLastName,
            userName = userUsername,
            userPassword = userPassword,
            userID = null
        )
    }

    // Boş Alan Kontrolü
    fun fieldControl(
        editTextFirstName: String,
        editTextLastName: String,
        editTextUserName: String,
        editTextPassword: String,
        editTextRePassword: String
    ): Boolean {
        if (editTextFirstName.isEmpty() ||
            editTextLastName.isEmpty() ||
            editTextUserName.isEmpty() ||
            editTextPassword.isEmpty() ||
            editTextRePassword.isEmpty()
        ) {
            return true
        }
        return false
    }

    fun passwordControl(
        editTextPassword: String,
        editTextRePassword: String
    ): Boolean {
        if (editTextPassword != editTextRePassword) {
            return true
        }
        return false
    }

    fun addUserList(
        editTextFirstName: String,
        editTextLastName: String,
        editTextUsername: String,
        editTextPassword: String,
        editTextRePassword: String
    ) {
        if (fieldControl(editTextFirstName, editTextLastName, editTextUsername, editTextPassword, editTextRePassword))
        {
            Toast.makeText(context, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show()

        } else {
            if (passwordControl(editTextPassword, editTextRePassword)) {
                Toast.makeText(context, "Girilen Şifreler Aynı Olmalı!", Toast.LENGTH_SHORT).show()
            } else {
                findSameData(editTextUsername)
            }
        }
    }
}