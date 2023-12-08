package com.example.fakestore.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.product_detail.ProductAdapter
import com.example.fakestore.R
import com.example.fakestore.model.Product
import com.example.fakestore.network.ApiClient
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var productList: List<Product> = listOf() // Initially empty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view_products)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productAdapter = ProductAdapter(productList) { product ->

            val intent = Intent(this, ActivityProductDetail::class.java).apply {
                putExtra("PRODUCT_ID", product.id)
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_to_cart).setOnClickListener {
            val intent = Intent(this, ActivityCart::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_to_order_history).setOnClickListener {
            val intent = Intent(this, ActivityOrderHistory::class.java)
            startActivity(intent)
        }

        recyclerView.adapter = productAdapter

        CoroutineScope(Dispatchers.Main).launch {
            loadProducts()
        }
    }



    private suspend fun loadProducts() {
        try {
            val response = ApiClient.instance.getProducts()
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    productList = response.body() ?: emptyList()
                    productAdapter.updateData(productList)
                }

            } else {
                Log.e("MainActivity", "Error: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Failure: ${e.message}")
        }
    }
}