package com.icanerdogan.warehousemanagementsystem.util


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityBarcodeScannerBinding

class BarcodeScannerActivity : AppCompatActivity() {
    private lateinit var barcodeScannerBinding: ActivityBarcodeScannerBinding
    private lateinit var codeScanner : CodeScanner

    private lateinit var clipboard: ClipboardManager
    private lateinit var clip: ClipData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barcodeScannerBinding = ActivityBarcodeScannerBinding.inflate(layoutInflater)
        val view = barcodeScannerBinding.root

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

        setupPermissions()
        codeScan()
    }

    private fun codeScan() {
        val scannerView = barcodeScannerBinding.scannerView

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clip = ClipData.newPlainText("BarcodeNumber", it.text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Barkod Numarası Kopyalandı!", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",  Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this,
        android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.CAMERA),
        1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> {
                if (grantResults.isEmpty() ||grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "You Need The Camera Permission!",  Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}