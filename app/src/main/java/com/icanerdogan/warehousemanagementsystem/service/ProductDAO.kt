package com.icanerdogan.warehousemanagementsystem.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.icanerdogan.warehousemanagementsystem.model.Product
import java.util.concurrent.Flow

@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(vararg product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts() : List<Product>

    @Query("DELETE FROM Product WHERE barcode = :productBarcode")
    suspend fun deleteProduct(productBarcode : Long?)

    @Query("SELECT * FROM Product WHERE barcode LIKE :barcode")
    suspend fun findBarcode(barcode: Long?): List<Product>

    @Query("SELECT * FROM Product WHERE barcode LIKE :barcode")
        suspend fun findOneBarcode(barcode: Long): List<Product>

    @Update
    suspend fun entryProduct(product: Product)

    /*
    @Update
    suspend fun update(vararg product: Product)

    @Query("DELETE FROM Product WHERE productID=:deleteID")
    suspend fun delete(deleteID:Int)
    */

}