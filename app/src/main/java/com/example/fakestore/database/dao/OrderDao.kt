package com.example.fakestore.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fakestore.model.OrderEntity

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: com.example.fakestore.model.Order)

    @Query("SELECT * FROM OrderEntity")
    suspend fun getAllOrders(): List<OrderEntity>
}