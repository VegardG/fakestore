package com.example.fakestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @PrimaryKey val id: Int,
    val products: List<CartItem>,
    val totalPrice: Double,
    val date: String
)