package com.icanerdogan.warehousemanagementsystem.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityAddProductBinding
import com.icanerdogan.warehousemanagementsystem.model.Product
import com.icanerdogan.warehousemanagementsystem.util.BarcodeScannerActivity
import com.icanerdogan.warehousemanagementsystem.viewmodel.AddProductViewModel

class AddProductActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
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
        // Action Bar
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Database
        database = Firebase.database.reference
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
                    editTextProductModel = addProductBinding.editTextProductModel.text.toString(),
                    editTextProductBarcodeNumber = addProductBinding.editTextProductBarcodeNumber.text.toString().toLong(),
                    productSelecetedCategoryItem =selecetedCategoryItem
                )

                // Firebase Added!
                database.child("products").child(addProductBinding.editTextProductBarcodeNumber.text.toString())
                    .setValue(Product(
                        null,
                        addProductBinding.editTextProductName.text.toString(),
                        addProductBinding.editTextProductBarcodeNumber.text.toString().toLong(),
                        addProductBinding.editTextProductModel.text.toString(),
                        0,
                        selecetedCategoryItem)
                    )
                dialog.cancel()
            }
            .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.barcode_button, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.barcode -> {
            val intent = Intent(this, BarcodeScannerActivity::class.java)
            startActivity(intent)
            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}

