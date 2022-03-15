package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityDeleteProductBinding
import com.icanerdogan.warehousemanagementsystem.viewmodel.DeleteProductViewModel

class DeleteProductActivity : AppCompatActivity() {
    private lateinit var deleteProductBinding: ActivityDeleteProductBinding
    private lateinit var deleteProductViewModel: DeleteProductViewModel

    //private lateinit var deleteModelName : String
    private var deleteBarcodeNumber: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteProductBinding = ActivityDeleteProductBinding.inflate(layoutInflater)
        val view = deleteProductBinding.root
        setContentView(view)
        // View Model
        deleteProductViewModel = ViewModelProvider(this)[DeleteProductViewModel::class.java]

        deleteProductBinding.floatingActionButtonDelete.setOnClickListener {
            if (fieldController()) {
                Toast.makeText(this, "Tüm Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show()
            } else {

                deleteBarcodeNumber = deleteProductBinding.editTextDeleteProductBarcodeNumber.text.toString().toLong()

                if (deleteProductViewModel.findedBarcode.value == null){
                    Toast.makeText(this, "Barkod Numarası Bulunamadı!", Toast.LENGTH_SHORT).show()
                }
                else{
                    deleteProductViewModel.deleteData(deleteBarcodeNumber)
                    Toast.makeText(this, "Ürün Başarıyla Silindi!", Toast.LENGTH_SHORT).show()
                }
            }
            deleteProductViewModel.findBarcodeData(deleteBarcodeNumber)
        }
    }

    private fun fieldController(): Boolean {
        if (//deleteProductBinding.editTextDeleteProductModel.text!!.isEmpty() &&
            deleteProductBinding.editTextDeleteProductBarcodeNumber.text!!.isEmpty()) {
            return true
        }
        return false
    }
}