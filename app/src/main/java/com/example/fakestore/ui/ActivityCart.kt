package com.example.fakestore.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.CartAdapter
import com.example.fakestore.CartManager
import com.example.fakestore.R
import com.example.fakestore.model.Order
import java.util.*
import java.text.SimpleDateFormat

class ActivityCart : AppCompatActivity() {
    private lateinit var cartAdapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var  totalPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.recycler_view_cart_items)
        totalPriceTextView = findViewById(R.id.text_view_total_price)

        recyclerView.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(CartManager.items) { updateTotalPrice() }
        recyclerView.adapter = cartAdapter

        updateTotalPrice()

        findViewById<Button>(R.id.button_checkout).setOnClickListener {
            performCheckout()
        }
        findViewById<Button>(R.id.button_back_to_main).setOnClickListener {
            finish()
        }
    }

    private fun performCheckout() {
        if (CartManager.items.isNotEmpty()) {
            val order = Order(
                id = generateOrderId(),
                products = CartManager.items.toList(),
                totalPrice = CartManager.items.sumOf { it.product.price * it.quantity },
                date = getCurrentDate()
            )
        } else {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice() {
        val total = CartManager.items.sumOf { it.product.price * it.quantity }
        totalPriceTextView.text = "Total Price: $${String.format("%.2f", total)}"
    }

    private fun generateOrderId(): Int {
        return ((Date().time / 1000L) % Integer.MAX_VALUE).toInt() + Random().nextInt(1000)
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }
}