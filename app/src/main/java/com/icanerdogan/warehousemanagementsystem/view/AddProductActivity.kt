package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityAddProductBinding
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityMainBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.viewmodel.AddProductViewModel

class AddProductActivity : AppCompatActivity() {
    private lateinit var addProductBinding: ActivityAddProductBinding
    private lateinit var addProductViewModel: AddProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addProductBinding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = addProductBinding.root
        setContentView(view)

        addProductViewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)



        addProductBinding.floatingActionButton.setOnClickListener {

            val product = Product(productName = addProductBinding.editTextProductName.text.toString(),
                productModel = addProductBinding.editTextProductModel.text.toString(),
                productStock = 0,
                productCategory = "",
                productID = null)

            addProductViewModel.addData(product)
            Toast.makeText(applicationContext,"Inserted",Toast.LENGTH_SHORT).show()
        }
    }


}