package com.example.fakestore.model

object OrderMapper {
    fun mapOrderEntityToOrder(orderEntity: OrderEntity): Order {
        return Order(
            id = orderEntity.orderId,
            products = emptyList(),
            totalPrice = orderEntity.totalPrice,
            date = orderEntity.date
        )
    }
}