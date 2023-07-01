package com.example.productinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productinfoapp.databinding.ActivityAddProductBinding
import com.example.productinfoapp.databinding.ActivityViewProductsBinding

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var binding2: ActivityViewProductsBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        binding2 = ActivityViewProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database instantiation
        databaseHelper = DatabaseHelper(this)

        //recyclerview setup
        recyclerView = binding2.recyclerviewViewProducts
        recyclerView.layoutManager = LinearLayoutManager(this)

        //data declaration
        productList = getData()
        recyclerView.adapter = ProductsAdapter(productList)

        //add item
        binding.btnAddProductAddProductActivity.setOnClickListener {
            var name = binding.etName.text.toString()
            var description = binding.etDescription.text.toString()

            if ((name == "") || (description == "")) {
                Toast.makeText(applicationContext, "Error: Product Name and/or Description is null", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            var newProduct = Product(0, name, description)
            //add new data to database table
            addData(newProduct)
            //add new product to list
            productList.add(newProduct)
            //notify adapter that data has changed
            recyclerView.adapter?.notifyDataSetChanged()

            //clear edittexts
            binding.etName.text = null
            binding.etDescription.text = null
        }
    }

    private fun getData(): MutableList<Product> {
        return databaseHelper.getAllProducts()
    }

    private fun addData(product: Product) {
        databaseHelper.insertProduct(product)
        Toast.makeText(applicationContext, "Product Added", Toast.LENGTH_SHORT).show()
    }

//    private fun update(id: Int, name: String, description: String) {
//        val productObject = Product(id, name, description)
//        databaseHelper.updateData(productObject)
//        getData()
//        Toast.makeText(applicationContext, "Product Updated", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun delete(id: Int) {
//        databaseHelper.deleteData(id)
//        getData()
//        Toast.makeText(applicationContext, "Product Deleted", Toast.LENGTH_SHORT).show()
//    }
}