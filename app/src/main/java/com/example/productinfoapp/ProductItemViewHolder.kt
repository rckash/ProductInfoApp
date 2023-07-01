package com.example.productinfoapp

import androidx.recyclerview.widget.RecyclerView
import com.example.productinfoapp.databinding.ProductItemLayoutBinding

class ProductItemViewHolder(private val binding: ProductItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.tvNameItemLayout.text = product.name
        binding.tvDescriptionItemLayout.text = product.description
    }
}