package com.example.fakestore.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.fakestore.order_history.OrderHistoryAdapter
import com.example.fakestore.order_history.OrderHistoryManager
import com.example.fakestore.R
import com.example.fakestore.database.AppDatabase
import com.example.fakestore.model.OrderMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityOrderHistory : AppCompatActivity() {

    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        recyclerView = findViewById(R.id.recycler_view_order_history)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadOrdersFromDatabase()

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish()
        }
    }

    private fun loadOrdersFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val ordersFromDb = db.orderDao().getAllOrders().map { OrderMapper.mapOrderEntityToOrder(it) }
            OrderHistoryManager.orders.clear()
            OrderHistoryManager.orders.addAll(ordersFromDb)

            withContext(Dispatchers.Main) {
                orderHistoryAdapter = OrderHistoryAdapter(OrderHistoryManager.orders)
                recyclerView.adapter = orderHistoryAdapter
            }
        }
    }
}