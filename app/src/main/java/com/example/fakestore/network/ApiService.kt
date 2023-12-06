package com.example.fakestore.network

import retrofit2.Call
import com.example.fakestore.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface ApiService {
    @GET("products")
    fun getProducts(): Deferred<Response<List<Product>>>

    @GET("products/{id}")
    fun getProductById(@Path("id") id: Int): Call<Product>
}