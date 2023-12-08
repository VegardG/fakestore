package com.example.fakestore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fakestore.Converters
import com.example.fakestore.dao.OrderDao
import com.example.fakestore.dao.ProductDao
import com.example.fakestore.model.Order
import com.example.fakestore.model.OrderEntity
import com.example.fakestore.model.ProductEntity

@Database(entities = [Order::class, ProductEntity::class, OrderEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
}