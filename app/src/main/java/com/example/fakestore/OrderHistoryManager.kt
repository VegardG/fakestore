package com.example.fakestore

import com.example.fakestore.model.Order

object OrderHistoryManager {
    val orders = mutableListOf<Order>()

    fun addOrder(order: Order) {
        orders.add(order)
    }
}