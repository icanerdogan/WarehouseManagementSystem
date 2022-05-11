package com.icanerdogan.warehousemanagementsystem.view.product

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.adapter.RecyclerViewStockAdapter
import com.icanerdogan.warehousemanagementsystem.databinding.ActivityStockBinding
import com.icanerdogan.warehousemanagementsystem.viewmodel.product.StockViewModel

class StockActivity : AppCompatActivity() {
    private lateinit var stockBinding: ActivityStockBinding
    private lateinit var stockViewModel: StockViewModel
    private val productAdapter = RecyclerViewStockAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stockBinding = ActivityStockBinding.inflate(layoutInflater)
        val view = stockBinding.root
        setContentView(view)

        // Action Bar
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}