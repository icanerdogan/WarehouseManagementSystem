package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.launch

class AddProductViewModel(application: Application):BaseViewModel(application) {
    var findedData = MutableLiveData<List<Product>>()

    fun addData(product: Product) {
        launch {
            ProductDatabase(getApplication()).productDao().insertProduct(product)
        }
    }

    fun findSameData(productModel : String, productBarcodeNumber: Long) {
        launch {
            val finded = ProductDatabase(getApplication()).productDao().findModelANDBarcode(productModel, productBarcodeNumber)
            showSame(productList = finded)
        }
    }

    private fun showSame(productList: List<Product>) {
        findedData.value = productList
    }

}