package com.icanerdogan.warehousemanagementsystem.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.icanerdogan.warehousemanagementsystem.model.Product

@Database(entities = arrayOf(Product::class), version = 2)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    companion object {

        @Volatile
        private var instance: ProductDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ProductDatabase::class.java, "productdatabase"
        ).fallbackToDestructiveMigration()
            .build()
    }
}