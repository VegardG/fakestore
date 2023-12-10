package com.example.fakestore.model


import com.example.fakestore.database.dao.ProductDao
import com.example.fakestore.model.Product
import com.example.fakestore.model.ProductMapper

class ProductRepository(private val productDao: ProductDao) {

    suspend fun getProducts(): List<Product> {
        return productDao.getAllProducts().map { ProductMapper.entityToDomain(it) }
    }

    suspend fun saveProduct(product: Product) {
        productDao.insertProduct(ProductMapper.domainToEntity(product))
    }


}