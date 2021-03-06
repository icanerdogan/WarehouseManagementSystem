package com.icanerdogan.warehousemanagementsystem.viewmodel.product

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.product.ProductDatabase
import com.icanerdogan.warehousemanagementsystem.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class DeleteProductViewModel(application: Application) : BaseViewModel(application) {
    var findedBarcode = MutableLiveData<List<Product>>()

    fun deleteData(productBarcodeNumber: Long) {
        launch {
            ProductDatabase(getApplication()).productDao().deleteProduct(productBarcodeNumber)
        }
    }

    fun findBarcodeData(productBarcodeNumber: Long) {
        launch {
            val findedBarcode =
                ProductDatabase(getApplication()).productDao().findBarcode(productBarcodeNumber)
            showBarcodes(productList = findedBarcode)
        }
    }

    private fun showBarcodes(productList: List<Product>) {
        findedBarcode.value = productList
    }


    suspend fun findBackupData(productBarcodeNumber: Long): List<Product> {
        return ProductDatabase(getApplication()).productDao().findBarcode(productBarcodeNumber)
    }
}