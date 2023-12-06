package com.example.fakestore

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fakestore.model.Product
import com.example.fakestore.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityProductDetail : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val imageView: ImageView = findViewById(R.id.image_product_detail)
        val titleView: TextView = findViewById(R.id.text_product_detail_title)
        val priceView: TextView = findViewById(R.id.text_product_detail_price)
        val descriptionView: TextView = findViewById(R.id.text_product_detail_description)
        val addToCartButton: Button = findViewById(R.id.button_add_to_cart)

        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        if (productId != -1) {
            getProductById(productId) { product ->
                titleView.text = product.title
                priceView.text = "$${product.price}"
                descriptionView.text = product.description
                Glide.with(this).load(product.images.firstOrNull()).into(imageView)
            }
        }

        addToCartButton.setOnClickListener {
            getProductById(productId) { product ->
                CartManager.addToCart(product)
                Toast.makeText(this@ActivityProductDetail, "${product.title} added to cart", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getProductById(productId: Int, onResult: (Product) -> Unit) {
        ApiClient.instance.getProductById(productId).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    response.body()?.let { product ->
                        onResult(product)
                    } ?: showError("Product not found")
                } else {
                    showError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                showError("Network error: ${t.message}")
            }
        })

        }
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}