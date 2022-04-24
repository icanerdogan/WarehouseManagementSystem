package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityDeleteProductBinding
import com.icanerdogan.warehousemanagementsystem.viewmodel.DeleteProductViewModel

class DeleteProductActivity : AppCompatActivity() {
    private lateinit var deleteProductBinding: ActivityDeleteProductBinding
    private lateinit var deleteProductViewModel: DeleteProductViewModel

    private var deleteBarcodeNumber: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteProductBinding = ActivityDeleteProductBinding.inflate(layoutInflater)
        val view = deleteProductBinding.root
        setContentView(view)
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
}
