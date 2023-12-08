package com.example.fakestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate =  true) val orderId: Int,
    val date: String,
    val totalPrice: Double,
)