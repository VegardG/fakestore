package com.example.fakestore.model

object ProductMapper {

    fun entityToDomain(productEntity: ProductEntity): Product {
        return Product(
            id = productEntity.id,
            title = productEntity.name,
            price = productEntity.price,
            description = "",
            category = Category(0, "", "", "", ""),
            images = emptyList(),
            creationAt = "",
            updatedAt = ""
        )
    }

    fun domainToEntity(product: Product): ProductEntity {
        return ProductEntity(
            id = product.id,
            name = product.title,
            price = product.price
        )
    }
}