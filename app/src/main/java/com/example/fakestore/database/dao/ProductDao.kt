package com.example.fakestore.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fakestore.model.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM ProductEntity")
    suspend fun getAllProducts(): List<ProductEntity>
}