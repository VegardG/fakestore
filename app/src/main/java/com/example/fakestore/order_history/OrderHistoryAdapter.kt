package com.example.fakestore.order_history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.R
import com.example.fakestore.model.CartItem
import com.example.fakestore.model.Order
import com.example.fakestore.model.OrderEntity

class OrderHistoryAdapter(private val orders: List<Order>) : RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderIdTextView: TextView = view.findViewById(R.id.orderIdTextView)
        val orderDateTextView: TextView = view.findViewById(R.id.orderDateTextView)
        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        // Add other views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.orderIdTextView.text = "Order ID: ${ order.id }"
        holder.orderDateTextView.text = "Order date: ${ order.date }"
        holder.totalPriceTextView.text = "Order price: $${ order.totalPrice }"
    }

    override fun getItemCount() = orders.size
}