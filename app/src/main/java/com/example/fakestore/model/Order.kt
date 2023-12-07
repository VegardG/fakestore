package com.example.fakestore.model

data class Order(
    val id: Int,
    val products: List<CartItem>,
    val totalPrice: Double,
    val date: String
)