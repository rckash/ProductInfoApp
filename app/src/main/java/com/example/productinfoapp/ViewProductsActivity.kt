package com.example.productinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productinfoapp.databinding.ActivityViewProductsBinding

class ViewProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewProductsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database instantiation
        databaseHelper = DatabaseHelper(this)

        //recyclerview setup
        recyclerView = binding.recyclerviewViewProducts
        recyclerView.layoutManager = LinearLayoutManager(this)

        //data declaration
        productList = getData()
        recyclerView.adapter = ProductsAdapter(productList)
    }

    private fun getData(): MutableList<Product> {
        return databaseHelper.getAllProducts()
    }
}