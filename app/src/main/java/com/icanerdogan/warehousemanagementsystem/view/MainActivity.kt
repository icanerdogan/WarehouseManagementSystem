package com.icanerdogan.warehousemanagementsystem.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
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

        mainBinding.imageButtonUrunEkle.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }
        mainBinding.imageButtonUrunCikisi.setOnClickListener {
            val intent = Intent(this, OutputProductActivity::class.java)
            startActivity(intent)
        }
        mainBinding.imageButtonUrunGirisi.setOnClickListener {
            val intent = Intent(this, EntryProductActivity::class.java)
            startActivity(intent)
        }
        mainBinding.imageButtonUrunSil.setOnClickListener {
            val intent = Intent(this, DeleteProductActivity::class.java)
            startActivity(intent)
        }
        mainBinding.imageButtonStokGoruntule.setOnClickListener {
            val intent = Intent(this, StockActivity::class.java)
            startActivity(intent)
        }
    }
}
