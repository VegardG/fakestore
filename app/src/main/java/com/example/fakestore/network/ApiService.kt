package com.example.fakestore.network

import retrofit2.Call
import com.example.fakestore.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>

    @GET("products/{id}")
    fun getProductById(@Path("id") id: Int): Call<Product>
}