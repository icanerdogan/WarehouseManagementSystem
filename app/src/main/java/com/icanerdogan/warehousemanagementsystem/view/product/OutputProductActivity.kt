package com.icanerdogan.warehousemanagementsystem.view.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityOutputProductBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.util.BarcodeScannerActivity
import com.icanerdogan.warehousemanagementsystem.viewmodel.product.OutputProductViewModel
import java.lang.StringBuilder

class OutputProductActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    private lateinit var outputProductViewModel: OutputProductViewModel
    private lateinit var outputProductBinding: ActivityOutputProductBinding

    private var outputBarcodeNumber: Long = 0
    private var updateStockAmount: Int = 0
    private var oldStockAmount: Int = 0
    private var newStockAmount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outputProductBinding = ActivityOutputProductBinding.inflate(layoutInflater)
        val view = outputProductBinding.root
        setContentView(view)
        // Action Bar
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Database
        database = Firebase.database.reference

        // ViewModel
        outputProductViewModel = ViewModelProvider(this)[OutputProductViewModel::class.java]

        outputProductViewModel.findedBarcode.observe(this) { productlist ->
            if (productlist.isEmpty()) {
                Toast.makeText(this, "Barkod Numaras?? Bulunamad??!", Toast.LENGTH_SHORT).show()
            } else {
                updateStock(productlist)
            }
        }

        outputProductBinding.floatingActionButton.setOnClickListener {
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("????lem Onay??")
                .setMessage("??r??n Eklemeyi Onayl??yor musun?")
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    dialog.cancel()
                }
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    dialog.cancel()
                    Toast.makeText(this, "??r??n ????karma ????lemi Ba??ar??s??z!", Toast.LENGTH_LONG).show()
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    findOutputProduct()
                    dialog.cancel()
                }
                .show()
        }
    }

    private fun updateStock(it: List<Product>) {
        outputBarcodeNumber = outputProductBinding.editTextOutputProductBarcodeNumber.text.toString().toLong()
        updateStockAmount = outputProductBinding.editTextOutputProductStock.text.toString().toInt()

        oldStockAmount = it[0].productStock.toString().toInt()

        if (oldStockAmount < updateStockAmount) {
            val text = StringBuilder("Elinizde Yeterli Stok Bulunamad??!")
                .append("\n")
                .append("G??ncel Stok: $oldStockAmount")

            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        } else {
            newStockAmount = oldStockAmount - updateStockAmount

            outputProductViewModel.updateData(stockAmount = newStockAmount, productBarcodeNumber = outputBarcodeNumber)

            // Firebase Updated!
            database.child("products").child(outputBarcodeNumber.toString()).child("productStock").setValue(newStockAmount)

            Toast.makeText(this, "??r??n ????k?????? Ba??ar??yla Ger??ekle??ti!", Toast.LENGTH_SHORT).show()
        }
    }

    // Observe Tetiklenmesi
    private fun findOutputProduct() {
        if (fieldController()) {
            Toast.makeText(this, "??ki Alanda Bo?? B??rak??lamaz!", Toast.LENGTH_LONG).show()
        } else {
            outputBarcodeNumber = outputProductBinding.editTextOutputProductBarcodeNumber.text.toString().toLong()
            outputProductViewModel.findBarcodeData(outputBarcodeNumber)
        }
    }

    // Bo?? Alan Kontrol??
    private fun fieldController(): Boolean {
        if (outputProductBinding.editTextOutputProductBarcodeNumber.text!!.isEmpty() ||
            outputProductBinding.editTextOutputProductStock.text!!.isEmpty()
        ) {
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