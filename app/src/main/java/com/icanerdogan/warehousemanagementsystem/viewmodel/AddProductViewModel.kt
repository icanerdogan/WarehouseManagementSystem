package com.icanerdogan.warehousemanagementsystem.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.icanerdogan.warehousemanagementsystem.MainApplication
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.service.ProductDatabase
import kotlinx.coroutines.launch

class AddProductViewModel(application: Application):BaseViewModel(application) {
    var findedData = MutableLiveData<List<Product>>()
    private var selecetedCategoryItem: String = "Select Category"
    private val context: Context = MainApplication.applicationContext()

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


    // Alınan Verileriden Ürün Yaratma!
     fun createProduct(
        productName: String,
        productBarcodeNumber: Long,
        productModel : String,
        productSelecetedCategoryItem : String)
     : Product {
        return Product(
            productName = productName,
            productBarcodeNumber = productBarcodeNumber,
            productModel = productModel,
            productStock = 0,
            productCategory = productSelecetedCategoryItem,
            productID = null
        )
    }

    // Boş Alan Kontrolü
     fun fieldControl(
        editTextProductName : String,
        editTextProductModel: String,
        editTextProductBarcodeNumber: Long,
        productSelecetedCategoryItem : String)
     : Boolean {
        if (editTextProductName.isEmpty() ||
            editTextProductModel.isEmpty() ||
            editTextProductBarcodeNumber.toString().isEmpty() ||
            selecetedCategoryItem == productSelecetedCategoryItem
        ) {
            return true
        }
        return false
    }

     fun addProductList(
         editTextProductName : String,
         editTextProductModel: String,
         editTextProductBarcodeNumber: Long,
         productSelecetedCategoryItem : String)
     {
        if (fieldControl(editTextProductName, editTextProductModel, editTextProductBarcodeNumber, productSelecetedCategoryItem)) {
            Toast.makeText(context, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show()
        } else {
            val addBarcodeNumber = editTextProductBarcodeNumber.toString().toLong()
            findSameData(editTextProductModel, addBarcodeNumber)
        }
     }

}