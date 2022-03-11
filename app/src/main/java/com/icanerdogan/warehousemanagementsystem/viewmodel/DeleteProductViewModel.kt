package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.launch

class DeleteProductViewModel(application: Application) : BaseViewModel(application) {
    var findedProduct = MutableLiveData<List<Product>>()

    fun deleteData(productBarcodeNumber: Long, productModel : String) {
        launch {
            ProductDatabase(getApplication()).productDao().deleteProduct(productBarcodeNumber, productModel)
        }
    }

    fun findData(productBarcodeNumber: Long){
        launch {
            val findedProduct = ProductDatabase(getApplication()).productDao().findBarcode(productBarcodeNumber)
            showProducts(productList = findedProduct)
        }
    }

    private fun showProducts(productList: List<Product>) {
        findedProduct.value = productList
    }
}