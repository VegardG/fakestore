package com.example.fakestore.cart

import com.example.fakestore.model.CartItem
import com.example.fakestore.model.Product

object CartManager {
    val items = mutableListOf<CartItem>()

    fun addToCart(product: Product, quantity: Int = 1) {
        val existingItem = items.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            items.add(CartItem(product, quantity))
        }
    }

    fun removeFromCart(productId: Int) {
        items.removeAll { it.product.id == productId }
    }

    fun clearCart() {
        items.clear()
    }
}