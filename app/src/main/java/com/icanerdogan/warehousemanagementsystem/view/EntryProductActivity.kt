package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityDeleteProductBinding
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityEntryProductBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.viewmodel.DeleteProductViewModel
import com.icanerdogan.warehousemanagementsystem.viewmodel.EntryProductViewModel

class EntryProductActivity : AppCompatActivity() {
    private lateinit var entryProductBinding: ActivityEntryProductBinding
    private lateinit var entryProductViewModel: EntryProductViewModel
/*
    private var entryBarcodeNumber: Long = 0
    private var updateStockAmount: Int = 0
    private var updatedProduct: Product? = null
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryProductBinding = ActivityEntryProductBinding.inflate(layoutInflater)
        val view = entryProductBinding.root
        setContentView(view)
        // View Model
        entryProductViewModel = ViewModelProvider(this)[EntryProductViewModel::class.java]

 /*       entryProductBinding.floatingActionButton.setOnClickListener {

            if (fieldController()) {
                Toast.makeText(this, "İki Alanda Boş Bırakılamaz!", Toast.LENGTH_LONG).show()
            } else {
                entryBarcodeNumber = entryProductBinding.editTextEntryProductBarcodeNumber.text.toString().toLong()
                updateStockAmount = entryProductBinding.editTextEntryProductStock.text.toString().toInt()

                updatedProduct = entryProductViewModel.getOneBarcode(entryBarcodeNumber)
                updatedProduct?.let {
                    it.productStock = updateStockAmount
                    entryProductViewModel.updateData(it)
                }

            }
*/
            /* if(entryProductViewModel.product.value == null){
                  Toast.makeText(this, "yok", Toast.LENGTH_LONG).show()
             }
             else{
                 entryProductViewModel.updateData(updatedProduct)
             }*/


        }

    }
/*    private fun fieldController(): Boolean {
        if (entryProductBinding.editTextEntryProductBarcodeNumber.text!!.isEmpty() ||
            entryProductBinding.editTextEntryProductStock.text!!.isEmpty()
        ) {
            return true
        }
        return false
    }
}*/


