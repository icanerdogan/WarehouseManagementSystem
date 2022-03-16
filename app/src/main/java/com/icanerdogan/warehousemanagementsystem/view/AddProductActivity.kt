package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityAddProductBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.viewmodel.AddProductViewModel

class AddProductActivity : AppCompatActivity() {
    private lateinit var addProductBinding: ActivityAddProductBinding
    private lateinit var addProductViewModel: AddProductViewModel
    private var selecetedCategoryItem: String = "Select Category"

    override fun onResume() {
        super.onResume()
        // Drop Down List
        val categories = resources.getStringArray(R.array.category_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_list, categories)
        addProductBinding.autoCompleteTextCategory.setAdapter(arrayAdapter)

        addProductBinding.autoCompleteTextCategory.setOnItemClickListener { adapterView, view, position, id ->
            selecetedCategoryItem = adapterView.getItemAtPosition(position).toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addProductBinding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = addProductBinding.root
        setContentView(view)

        // View Model
        addProductViewModel = ViewModelProvider(this)[AddProductViewModel::class.java]


        // Add Click
        addProductViewModel.findedData.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, "Aynı Bilgilere Sahip Ürün Bulundu!", Toast.LENGTH_SHORT).show()
            }
            else{
                addProductViewModel.addData(createProduct())
                Toast.makeText(applicationContext, "Ürün Başarıyla Eklendi!", Toast.LENGTH_SHORT).show()
            }
        }
        addProductBinding.floatingActionButton.setOnClickListener {
            addProductList()
        }
    }

    private fun addProductList() {
        if (fieldControl()) {
            Toast.makeText(this, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show()
        } else {
            val addBarcodeNumber = addProductBinding.editTextProductBarcodeNumber.text.toString().toLong()
            val addModel = addProductBinding.editTextProductModel.text.toString()
            addProductViewModel.findSameData(addModel, addBarcodeNumber)
        }
    }


    // Boş Alan Kontrolü
    private fun fieldControl(): Boolean {
        if (addProductBinding.editTextProductName.text!!.isEmpty() ||
            addProductBinding.editTextProductModel.text!!.isEmpty() ||
            addProductBinding.editTextProductBarcodeNumber.text!!.isEmpty() ||
            selecetedCategoryItem == "Select Category"
        ) {
            return true
        }
        return false
    }

    // Alınan Verileriden Ürün Yaratma
    private fun createProduct(): Product {
        return Product(
            productName = addProductBinding.editTextProductName.text.toString(),
            productBarcodeNumber = addProductBinding.editTextProductBarcodeNumber.text.toString().toLong(),
            productModel = addProductBinding.editTextProductModel.text.toString(),
            productStock = 0,
            productCategory = selecetedCategoryItem,
            productID = null
        )
    }

}