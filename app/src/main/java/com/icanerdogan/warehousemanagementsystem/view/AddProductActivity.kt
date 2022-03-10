package com.icanerdogan.warehousemanagementsystem.view

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.adapter.RecyclerViewStockAdapter
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
            if (addProductBinding.editTextProductName.text.isEmpty() ||
                addProductBinding.editTextProductModel.text.isEmpty()
            ) {
                Toast.makeText(this, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show()
            } else {
                val product = Product(
                    productName = addProductBinding.editTextProductName.text.toString(),
                    productModel = addProductBinding.editTextProductModel.text.toString(),
                    productStock = 0,
                    productCategory = "",
                    productID = null
                )

                addProductViewModel.addData(product)
                Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_SHORT).show()
            }

        }
    }


}