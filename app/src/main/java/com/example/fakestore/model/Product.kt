package com.example.fakestore.model

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: List<String>,
    val images: List<String>
)

data class Category(
    val id: Int,
    val name: String,
    val image: String
)