package com.example.fakestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.model.CartItem

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onItemRemoved: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.image_product)
        val productName: TextView = view.findViewById(R.id.text_product_name)
        val productPrice: TextView = view.findViewById(R.id.text_product_price)
        val removeButton: Button = view.findViewById(R.id.remove_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return // Safeguard against invalid positions

        // Reset or clear ViewHolder's views here if necessary
        holder.productImage.setImageResource(0) // Reset image view
        holder.productName.text = "" // Clear text view
        holder.productPrice.text = "" // Clear text view

        // Set new data
        holder.productName.text = item.product.title
        holder.productPrice.text = "$${item.product.price}"
        if (item.product.images.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(item.product.images.first())
                .into(holder.productImage)
        } else {
            Glide.with(holder.itemView.context)
                .load(R.drawable.fakestore)
                .into(holder.productImage)
        }

        holder.removeButton.setOnClickListener {
            try {
                val currentPosition = holder.bindingAdapterPosition
                if (currentPosition != RecyclerView.NO_POSITION && currentPosition < items.size) {
                    CartManager.removeFromCart(item.product.id)
                    items.removeAt(currentPosition)
                    notifyItemRemoved(currentPosition)
                    notifyItemRangeChanged(currentPosition, items.size)
                    onItemRemoved()
                }
            } catch (e: Exception) {
                Toast.makeText(holder.itemView.context, "Error removing product: ${e.message}", Toast.LENGTH_SHORT).show()

            }
        }
    }
    override fun getItemCount() = items.size
}