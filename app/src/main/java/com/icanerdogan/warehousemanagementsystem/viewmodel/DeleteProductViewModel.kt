package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.launch

class DeleteProductViewModel(application: Application) : BaseViewModel(application) {
    fun deleteData(productBarcodeNumber: Long, productModel : String) {
        launch {
            ProductDatabase(getApplication()).productDao().deleteProduct(productBarcodeNumber, productModel)
        }
    }
}