package com.example.fakestore.model

data class Order(
    val id: Int,
    val products: List<Product>,
    val totalPrice: Double,
    val date: String
)