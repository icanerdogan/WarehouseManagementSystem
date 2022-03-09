package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.launch

class AddProductViewModel(application: Application):BaseViewModel(application) {
    //val addProductLiveData = MutableLiveData<Product>()

    fun addData(product: Product) {
        launch {

            ProductDatabase(getApplication()).productDao().insertProduct(product)
        }

    }
}