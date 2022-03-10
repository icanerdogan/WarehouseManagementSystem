package com.icanerdogan.warehousemanagementsystem.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.icanerdogan.warehousemanagementsystem.model.Product

@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(vararg product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts() : List<Product>

    /*
    @Update
    suspend fun update(vararg product: Product)

    @Query("DELETE FROM Product WHERE productID=:deleteID")
    suspend fun delete(deleteID:Int)
    */

}