package com.example.fakestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.model.CartItem

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onItemRemoved: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Bind views here, e.g., TextView for product name, price, etc.
        // Add a button or an icon to remove the item from the cart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        // Set item details to holder views

        holder.itemView.findViewById<Button>(R.id.remove_button).setOnClickListener {
            CartManager.removeFromCart(item.product.id)
            items.removeAt(position)
            notifyItemRemoved(position)
            onItemRemoved()
        }
    }

    override fun getItemCount() = items.size
}