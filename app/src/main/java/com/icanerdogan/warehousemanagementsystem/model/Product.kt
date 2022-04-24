package com.icanerdogan.warehousemanagementsystem.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

 @Entity(tableName = "Product")
 data class Product(
    @PrimaryKey(autoGenerate = true)
    val productID: Int?,

    @ColumnInfo(name = "name")
    val productName: String?,

    @ColumnInfo(name = "barcode")
    val productBarcodeNumber: Long?,

    @ColumnInfo(name = "model")
    val productModel: String?,

    @ColumnInfo(name = "stock")
    var productStock: Int?,

    @ColumnInfo(name = "category")
    val productCategory: String?
 )

