package com.example.fakestore.model

import com.example.fakestore.model.Product
import com.example.fakestore.model.ProductEntity

object ProductMapper {

    fun entityToDomain(productEntity: ProductEntity): Product {
        return Product(
            id = productEntity.id,
            title = productEntity.name,
            price = productEntity.price,
            description = "", // Default or map from another source if available
            category = Category(0, "", "", "", ""), // Default or map from another source
            images = emptyList(), // Default or map from another source
            creationAt = "", // Default or map from another source
            updatedAt = "" // Default or map from another source
        )
    }

    fun domainToEntity(product: Product): ProductEntity {
        return ProductEntity(
            id = product.id,
            name = product.title,
            price = product.price
            // Note: Only id, name, and price are mapped as ProductEntity has only these fields
        )
    }
}