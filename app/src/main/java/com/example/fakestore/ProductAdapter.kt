package com.example.fakestore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val productList: List<Product>,
    private val onProductClick: (Product) -> Unit // Callback for click events
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_product)
        val titleView: TextView = view.findViewById(R.id.text_product_title)
        val priceView: TextView = view.findViewById(R.id.text_product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        Picasso.get().load(product.images.firstOrNull()).into(holder.imageView)
        holder.titleView.text = product.title
        holder.priceView.text = "$${product.price}"

        // Handle click event
        holder.itemView.setOnClickListener { onProductClick(product) }
    }

    override fun getItemCount() = productList.size
}