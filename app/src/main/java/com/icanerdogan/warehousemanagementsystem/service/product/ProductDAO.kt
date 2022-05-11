package com.icanerdogan.warehousemanagementsystem.service.product

import androidx.room.*
import com.icanerdogan.warehousemanagementsystem.model.Product

@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(vararg product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Query("DELETE FROM Product WHERE barcode = :productBarcode")
    suspend fun deleteProduct(productBarcode: Long?)

    @Query("SELECT * FROM Product WHERE barcode LIKE :barcode")
    suspend fun findBarcode(barcode: Long?): List<Product>

    @Query("SELECT * FROM Product WHERE model LIKE :model OR barcode LIKE :barcode")
    suspend fun findModelANDBarcode(model: String?, barcode: Long?): List<Product>

    @Query("UPDATE Product SET stock =:productstock WHERE barcode =:productbarcode")
    suspend fun updateStockProduct(productstock: Int?, productbarcode: Long?)
}

