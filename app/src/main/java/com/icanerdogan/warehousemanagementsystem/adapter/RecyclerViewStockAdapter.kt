package com.icanerdogan.warehousemanagementsystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.icanerdogan.warehousemanagementsystem.R
import com.icanerdogan.warehousemanagementsystem.databinding.StockRowLayoutBinding
import com.icanerdogan.warehousemanagementsystem.model.Product

class RecyclerViewStockAdapter(private val productList : ArrayList<Product>) : RecyclerView.Adapter<RecyclerViewStockAdapter.RowHolder>() {

    class RowHolder(val stockRowLayoutBinding: StockRowLayoutBinding) : RecyclerView.ViewHolder(stockRowLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(StockRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.stockRowLayoutBinding.stockproduct = productList[position]
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<Product>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }
}