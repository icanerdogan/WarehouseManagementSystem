package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityOutputProductBinding
import com.icanerdogan.warehousemanagementsystem.viewmodel.OutputProductViewModel

class OutputProductActivity : AppCompatActivity() {
    private lateinit var outputProductViewModel: OutputProductViewModel
    private lateinit var outputProductBinding : ActivityOutputProductBinding

    private var outputBarcodeNumber: Long = 0
    private var updateStockAmount: Int = 0
    private var oldStockAmount: Int = 0
    private var newStockAmount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outputProductBinding = ActivityOutputProductBinding.inflate(layoutInflater)
        val view = outputProductBinding.root
        setContentView(view)

        outputProductViewModel = ViewModelProvider(this)[OutputProductViewModel::class.java]

        outputProductViewModel.findedBarcode.observe(this, Observer {
            if (it.isEmpty()) {
                Toast.makeText(this, "Barkod Numarası Bulunamadı!", Toast.LENGTH_SHORT).show()
            } else {
                outputBarcodeNumber = outputProductBinding.editTextOutputProductBarcodeNumber.text.toString().toLong()
                updateStockAmount = outputProductBinding.editTextOutputProductStock.text.toString().toInt()

                oldStockAmount = it[0].productStock.toString().toInt()

                if (oldStockAmount < updateStockAmount){
                    Toast.makeText(this, "Elinizde Yeterli Stok Bulunamadı! Güncel Stok: $oldStockAmount", Toast.LENGTH_LONG).show()
                }
                else{
                    newStockAmount = oldStockAmount - updateStockAmount

                    outputProductViewModel.updateData(
                        stockAmount = newStockAmount,
                        productBarcodeNumber = outputBarcodeNumber
                    )
                    Toast.makeText(this, "Ürün Çıkışı Başarıyla Gerçekleşti!", Toast.LENGTH_SHORT).show()
                }

            }
        })

        outputProductBinding.floatingActionButton.setOnClickListener {
            if (fieldController()) {
                Toast.makeText(this, "İki Alanda Boş Bırakılamaz!", Toast.LENGTH_LONG).show()
            }
            else {
                outputBarcodeNumber = outputProductBinding.editTextOutputProductBarcodeNumber.text.toString().toLong()
                outputProductViewModel.findBarcodeData(outputBarcodeNumber)
            }
        }
    }

    private fun fieldController(): Boolean {
        if (outputProductBinding.editTextOutputProductBarcodeNumber.text!!.isEmpty() ||
            outputProductBinding.editTextOutputProductStock.text!!.isEmpty()
        ) {
            return true
        }
        return false
    }
}