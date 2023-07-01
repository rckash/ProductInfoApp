package com.example.productinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productinfoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //buttons
        binding.btnViewAllProducts.setOnClickListener {
            val switchActivity = Intent(this, ViewProductsActivity::class.java)
            startActivity(switchActivity)
        }
        binding.btnAddProduct.setOnClickListener {
            val switchActivity = Intent(this, AddProductActivity::class.java)
            startActivity(switchActivity)
        }
    }
}