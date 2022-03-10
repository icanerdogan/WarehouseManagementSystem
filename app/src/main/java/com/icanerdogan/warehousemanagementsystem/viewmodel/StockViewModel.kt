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
            showCountries(productList = productsData)
        }
    }

    private fun showCountries(productList: List<Product>) {
        products.value = productList
    }
}