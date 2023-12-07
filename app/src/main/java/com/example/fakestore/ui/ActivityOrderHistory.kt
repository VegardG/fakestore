package com.example.fakestore.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.OrderHistoryAdapter
import com.example.fakestore.OrderHistoryManager
import com.example.fakestore.R

class ActivityOrderHistory : AppCompatActivity() {

    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        recyclerView = findViewById(R.id.recycler_view_order_history)
        recyclerView.layoutManager = LinearLayoutManager(this)

        orderHistoryAdapter = OrderHistoryAdapter(OrderHistoryManager.getOrders())
        recyclerView.adapter = orderHistoryAdapter

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish()
        }
    }
}