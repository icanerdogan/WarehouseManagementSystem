package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityAddProductBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.viewmodel.AddProductViewModel

class AddProductActivity : AppCompatActivity() {

    private var selecetedCategoryItem: String = "Select Category"
    // Kategori seçimi
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

    private lateinit var addProductBinding: ActivityAddProductBinding
    private lateinit var addProductViewModel: AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addProductBinding = ActivityAddProductBinding.inflate(layoutInflater)
        val view = addProductBinding.root
        setContentView(view)
        // View Model
        addProductViewModel = ViewModelProvider(this)[AddProductViewModel::class.java]
        // Observe
        addProductViewModel.findedData.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, "Aynı Bilgilere Sahip Ürün Bulundu!", Toast.LENGTH_SHORT).show()
            }
            else{
                addProductViewModel.addData(
                    addProductViewModel.createProduct(
                        productName = addProductBinding.editTextProductName.text.toString(),
                        productBarcodeNumber = addProductBinding.editTextProductBarcodeNumber.text.toString().toLong(),
                        productModel = addProductBinding.editTextProductModel.text.toString(),
                        productSelecetedCategoryItem = selecetedCategoryItem
                    )
                )
                Toast.makeText(applicationContext, "Ürün Başarıyla Eklendi!", Toast.LENGTH_SHORT).show()
            }
        }

        addProductBinding.floatingActionButton.setOnClickListener {
            MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
            .setTitle("İşlem Onayı")
            .setMessage("Ürün Eklemeyi Onaylıyor musun?")
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                dialog.cancel()
            }
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                dialog.cancel()
                Toast.makeText(this, "Ürün Ekleme İşlemi Başarısız!", Toast.LENGTH_LONG).show()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                addProductViewModel.addProductList(
                    editTextProductName = addProductBinding.editTextProductName.text.toString(),
                    editTextProductModel =addProductBinding.editTextProductModel.text.toString(),
                    editTextProductBarcodeNumber =addProductBinding.editTextProductBarcodeNumber.text.toString().toLong(),
                    productSelecetedCategoryItem =selecetedCategoryItem
                )
                dialog.cancel()
            }
            .show()
        }
    }
}

