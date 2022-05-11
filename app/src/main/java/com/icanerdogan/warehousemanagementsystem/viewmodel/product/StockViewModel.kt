package com.icanerdogan.warehousemanagementsystem.viewmodel.product

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.product.ProductDatabase
import com.icanerdogan.warehousemanagementsystem.viewmodel.BaseViewModel
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