package com.icanerdogan.warehousemanagementsystem.view.user

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.databinding.ActivitySignInBinding
import com.icanerdogan.warehousemanagementsystem.view.MainActivity
import com.icanerdogan.warehousemanagementsystem.viewmodel.user.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SignInActivity : AppCompatActivity() {
    private lateinit var signInBinding: ActivitySignInBinding
    private lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        val view = signInBinding.root
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
        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        // Login
        signInBinding.buttonSignIn.setOnClickListener {
            if (signInViewModel.fieldControl(signInBinding.editTextSignInUsername.text.trim().toString(), signInBinding.editTextSignInPassword.text.trim().toString()))
            {
                Toast.makeText(this, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show()
            } else {
                controlUserData()
            }
        }

        // to SignUp
        signInBinding.buttonSignInToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun controlUserData(){
        runBlocking {
            if (signInViewModel.controlData(signInBinding.editTextSignInUsername.text.trim().toString(), signInBinding.editTextSignInPassword.text.trim().toString()))
            {
                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@SignInActivity, "Giriş Bilgileriniz Hatalıdır! Kontrol ediniz.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}