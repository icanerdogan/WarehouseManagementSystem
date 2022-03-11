package com.icanerdogan.warehousemanagementsystem.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.icanerdogan.warehousemanagementsystem.model.Product

@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(vararg product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts() : List<Product>

    @Query("DELETE FROM Product WHERE barcode = :productBarcode OR model = :productModel")
    suspend fun deleteProduct(productBarcode : Long, productModel: String)

    /*
    @Update
    suspend fun update(vararg product: Product)

    @Query("DELETE FROM Product WHERE productID=:deleteID")
    suspend fun delete(deleteID:Int)
    */

}