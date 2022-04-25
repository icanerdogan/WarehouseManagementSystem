package com.icanerdogan.warehousemanagementsystem.view

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityDeleteProductBinding
import com.icanerdogan.warehousemanagementsystem.util.BarcodeScannerActivity
import com.icanerdogan.warehousemanagementsystem.viewmodel.DeleteProductViewModel


class DeleteProductActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var query : Query

    private lateinit var deleteProductBinding: ActivityDeleteProductBinding
    private lateinit var deleteProductViewModel: DeleteProductViewModel

    private var deleteBarcodeNumber: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteProductBinding = ActivityDeleteProductBinding.inflate(layoutInflater)
        val view = deleteProductBinding.root
        setContentView(view)
        // Action Bar
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Database
        database = Firebase.database.reference

        // View Model
        deleteProductViewModel = ViewModelProvider(this)[DeleteProductViewModel::class.java]

        // Observe
        deleteProductViewModel.findedBarcode.observe(this){
            if (it.isEmpty()){
                Toast.makeText(this, "Ürün Bulunamadı!", Toast.LENGTH_SHORT).show()
            }
            else{
                deleteProduct()
            }
        }

        // Tıklandığında Observe tetiklenecek!
        deleteProductBinding.floatingActionButtonDelete.setOnClickListener {
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("İşlem Onayı")
                .setMessage("Ürünü Silmeyi Onaylıyor musun?")
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    dialog.cancel()
                }
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    dialog.cancel()
                    Toast.makeText(this, "Ürün Silme İşlemi Başarısız!", Toast.LENGTH_LONG).show()
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    findDeleteProduct()
                    dialog.cancel()
                }
                .show()
        }
    }


    // Ürünü Sil!
    private fun deleteProduct(){
        deleteProductViewModel.deleteData(deleteBarcodeNumber)
        database.child("products").child(deleteProductBinding.editTextDeleteProductBarcodeNumber.text.toString()).removeValue()
        Toast.makeText(this, "Ürün Başarıyla Silindi!", Toast.LENGTH_SHORT).show()
    }

    // ViewModel Barkod Bul!
    private fun findDeleteProduct(){
        if (fieldController()) {
            Toast.makeText(this, "Tüm Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show()
        } else {
            deleteBarcodeNumber = deleteProductBinding.editTextDeleteProductBarcodeNumber.text.toString().toLong()
            deleteProductViewModel.findBarcodeData(deleteBarcodeNumber)
        }
    }

    // Boş Alan Kontrolü!
    private fun fieldController(): Boolean {
        if (deleteProductBinding.editTextDeleteProductBarcodeNumber.text!!.isEmpty()) {
            return true
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.barcode_button, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.barcode -> {
            val intent = Intent(this, BarcodeScannerActivity::class.java)
            startActivity(intent)
            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
