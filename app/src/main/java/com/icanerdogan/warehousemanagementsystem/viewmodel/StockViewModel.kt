package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.launch

class StockViewModel(application: Application) : BaseViewModel(application) {
    val products = MutableLiveData<List<Product>>()

    fun getAllProductsData(){
        launch {
            val productsData = ProductDatabase(getApplication()).productDao().getAllProducts()
            showProducts(productList = productsData)
        }
    }

    private fun showProducts(productList: List<Product>) {
        products.value = productList
    }
}