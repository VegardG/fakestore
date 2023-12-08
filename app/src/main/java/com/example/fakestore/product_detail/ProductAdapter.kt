package com.example.fakestore.product_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.model.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val onProductClick: (Product) -> Unit // Callback for click events
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_product)
        val titleView: TextView = view.findViewById(R.id.text_product_title)
        val priceView: TextView = view.findViewById(R.id.text_product_price)
        val categoryView: TextView = view.findViewById(R.id.text_product_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        Glide.with(holder.itemView.context).load(product.images.firstOrNull()).into(holder.imageView)
        holder.titleView.text = product.title
        holder.priceView.text = "$${product.price}"
        holder.categoryView.text = product.category.name

        // Handle click event
        holder.itemView.setOnClickListener { onProductClick(product) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }

    override fun getItemCount() = productList.size
}