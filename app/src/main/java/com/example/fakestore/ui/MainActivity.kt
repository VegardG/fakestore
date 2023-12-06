package com.example.fakestore.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.ActivityProductDetail
import com.example.fakestore.ProductAdapter
import com.example.fakestore.R
import com.example.fakestore.model.Product
import com.example.fakestore.network.ApiClient
import com.example.fakestore.ui.theme.FakestoreTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            // Handle product click
            // For example, start an activity with the product details
            val intent = Intent(this, ActivityProductDetail::class.java).apply {
                putExtra("PRODUCT_ID", product.id)
            }
            startActivity(intent)
        }

        recyclerView.adapter = productAdapter

        // Load products from API
        loadProducts()
    }

    private fun loadProducts() {
        // Use your ApiClient to load products
        // This is a simplified example, you should do this in a background thread
        ApiClient.instance.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    productList = response.body() ?: emptyList()
                    productAdapter.notifyDataSetChanged() // Refresh the adapter
                } else {
                    Log.e("MainActivity", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}