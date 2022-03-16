package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityDeleteProductBinding
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityEntryProductBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.viewmodel.DeleteProductViewModel
import com.icanerdogan.warehousemanagementsystem.viewmodel.EntryProductViewModel

class EntryProductActivity : AppCompatActivity() {
    private lateinit var entryProductBinding: ActivityEntryProductBinding
    private lateinit var entryProductViewModel: EntryProductViewModel
    private var entryBarcodeNumber: Long = 0
    private var updateStockAmount: Int = 0
    private var oldStockAmount: Int = 0
    private var newStockAmount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryProductBinding = ActivityEntryProductBinding.inflate(layoutInflater)
        val view = entryProductBinding.root
        setContentView(view)

        // View Model
        entryProductViewModel = ViewModelProvider(this)[EntryProductViewModel::class.java]

        entryProductViewModel.findedBarcode.observe(this) { productlist ->
            if (productlist.isEmpty()) {
                Toast.makeText(this, "Barkod Numarası Bulunamadı!", Toast.LENGTH_SHORT).show()
            } else {
                updateStock(productlist)
            }
        }
        entryProductBinding.floatingActionButton.setOnClickListener {
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
                .setTitle("İşlem Onayı")
                .setMessage("Stok Eklemeyi Onaylıyor musun?")
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                    dialog.cancel()
                }
                .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                    dialog.cancel()
                    Toast.makeText(this, "Stok Ekleme İşlemi Başarısız!", Toast.LENGTH_LONG).show()
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    findEntryProduct()
                    dialog.cancel()
                }
                .show()
        }
    }

    // Change Stock
    private fun updateStock(it : List<Product>){
        entryBarcodeNumber = entryProductBinding.editTextEntryProductBarcodeNumber.text.toString().toLong()
        updateStockAmount = entryProductBinding.editTextEntryProductStock.text.toString().toInt()

        oldStockAmount = it[0].productStock.toString().toInt()

        newStockAmount = oldStockAmount + updateStockAmount

        entryProductViewModel.updateData(stockAmount = newStockAmount, productBarcodeNumber = entryBarcodeNumber)
        Toast.makeText(this, "Ürün Girişi Başarıyla Gerçekleşti!", Toast.LENGTH_SHORT).show()
    }

    // Button Click
    private fun findEntryProduct() {
        if (fieldController()) {
            Toast.makeText(this, "İki Alanda Boş Bırakılamaz!", Toast.LENGTH_LONG).show()
        } else {
            entryBarcodeNumber = entryProductBinding.editTextEntryProductBarcodeNumber.text.toString().toLong()
            entryProductViewModel.findBarcodeData(entryBarcodeNumber)
        }
    }

    // Boş Alan Kontrolü!
    private fun fieldController(): Boolean {
        if (entryProductBinding.editTextEntryProductBarcodeNumber.text!!.isEmpty() ||
            entryProductBinding.editTextEntryProductStock.text!!.isEmpty()
        ) {
            return true
        }
        return false
    }

}


