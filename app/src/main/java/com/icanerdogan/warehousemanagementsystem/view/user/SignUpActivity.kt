package com.icanerdogan.warehousemanagementsystem.view.user

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivitySignInBinding
import com.icanerdogan.warehousemanagementsystem.databinding.ActivitySignUpBinding
import com.icanerdogan.warehousemanagementsystem.viewmodel.user.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        setContentView(view)

        // View Model
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        // Observe
        signUpViewModel.findedUsernameData.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, "Aynı Kullanıcı Adına Sahip Kullanıcı Bulundu!", Toast.LENGTH_SHORT).show()
            }
            else{
                signUpViewModel.addUser(
                    signUpViewModel.createUser(
                        userFirstName = signUpBinding.editTextSignUpName.text.trim().toString(),
                        userLastName = signUpBinding.editTextSignUpSurname.text.trim().toString(),
                        userUsername = signUpBinding.editTextSignUpUsername.text.trim().toString(),
                        userPassword = signUpBinding.editTextSignUpPassword.text.toString()
                    )
                )
                Toast.makeText(applicationContext, "Kayıt olma işlemi başarılı!", Toast.LENGTH_SHORT).show()
            }
        }

        // Register
        signUpBinding.buttonSignUp.setOnClickListener {
            signUpViewModel.addUserList(
                editTextFirstName = signUpBinding.editTextSignUpName.text.trim().toString(),
                editTextLastName = signUpBinding.editTextSignUpSurname.text.trim().toString(),
                editTextUsername = signUpBinding.editTextSignUpUsername.text.trim().toString(),
                editTextPassword = signUpBinding.editTextSignUpPassword.text.toString(),
                editTextRePassword = signUpBinding.editTextSignUpConfirmPassword.text.toString()
            )
        }

        // to SignIn
        signUpBinding.buttonSignUpToSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}