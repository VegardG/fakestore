package com.example.fakestore.model


import com.example.fakestore.database.dao.ProductDao
import com.example.fakestore.model.Product
import com.example.fakestore.model.ProductMapper

class ProductRepository(private val productDao: ProductDao) {

    suspend fun getProducts(): List<Product> {
        // Fetch products from the database and convert them to domain models
        return productDao.getAllProducts().map { ProductMapper.entityToDomain(it) }
    }

    suspend fun saveProduct(product: Product) {
        // Convert the domain model to an entity and save it to the database
        productDao.insertProduct(ProductMapper.domainToEntity(product))
    }

    // Add other methods as needed for interacting with products
}