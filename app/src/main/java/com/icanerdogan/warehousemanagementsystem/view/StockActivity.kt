package com.icanerdogan.warehousemanagementsystem.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.adapter.RecyclerViewStockAdapter
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityAddProductBinding
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityStockBinding
import com.icanerdogan.warehousemanagementsystem.viewmodel.AddProductViewModel
import com.icanerdogan.warehousemanagementsystem.viewmodel.StockViewModel

class StockActivity : AppCompatActivity() {
    private lateinit var stockBinding: ActivityStockBinding
    private lateinit var stockViewModel: StockViewModel
    private val productAdapter = RecyclerViewStockAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stockBinding = ActivityStockBinding.inflate(layoutInflater)
        val view = stockBinding.root
        setContentView(view)

        // Adapter
        stockBinding.recyclerViewStock.layoutManager = LinearLayoutManager(applicationContext)
        stockBinding.recyclerViewStock.adapter = productAdapter

        // View Model
        stockViewModel = ViewModelProvider(this)[StockViewModel::class.java]

        stockViewModel.products.observe(this) { products ->
            products?.let {
                productAdapter.updateProductList(products)
            }

        }
        // Observe Tetiklenmesi
        stockViewModel.getAllProductsData()
    }
}