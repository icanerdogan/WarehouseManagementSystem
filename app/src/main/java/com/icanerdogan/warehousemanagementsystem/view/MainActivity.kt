package com.icanerdogan.warehousemanagementsystem.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide()
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
