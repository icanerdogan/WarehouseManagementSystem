package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EntryProductViewModel(application: Application) : BaseViewModel(application) {
/*     private lateinit var product : MutableLiveData<Product>

    fun updateData(product : Product) {
        launch {
            ProductDatabase(getApplication()).productDao().entryProduct(product)

        }
    }
    fun getOneBarcode(barcode : Long) {
        launch {
            val getproduct = ProductDatabase(getApplication()).productDao().findOneBarcode(barcode)
            showProducts(getproduct)
        }
    }

    private fun showProducts(getproduct: Product) {
        product.value = getproduct
    }*/
}