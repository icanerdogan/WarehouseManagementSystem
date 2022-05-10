package com.icanerdogan.warehousemanagementsystem.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityIntroBinding
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityMainBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var introBinding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        introBinding = ActivityIntroBinding.inflate(layoutInflater)
        val view = introBinding.root
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

        introBinding.buttonSignIn.setOnClickListener {
            clickSignIn()
        }

        introBinding.buttonSignUp.setOnClickListener {
            clickSignUp()
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun clickSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}