package com.example.productinfoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "products.db"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
                CREATE TABLE product (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    description TEXT
                )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS products")
        onCreate(db)
    }

    //CRUD

    //CREATE
    fun insertProduct(product: Product) {
        val db = writableDatabase

        val sql = "INSERT INTO product (name, description) VALUES (?, ?)"
        val args = arrayOf(product.name, product.description)
        db.execSQL(sql, args)
    }
    //READ
    fun getAllProducts(): MutableList<Product> {
        val db = readableDatabase

        val cursor = db.rawQuery("SELECT * FROM product", null)
        val productList = mutableListOf<Product>()

        while(cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val description = cursor.getString(2)

            var newProduct = Product(id, name, description)
            productList.add(newProduct)
        }

        cursor.close()
        return productList
    }
    //UPDATE
    fun updateData(product: Product) {
        val db = writableDatabase
        val updateQuery = "UPDATE product SET name='${product.name}', description='${product.name}' WHERE id=${product.id};"
        db.execSQL(updateQuery)
    }
    //DELETE
    fun deleteData(id: Int) {
        val db = writableDatabase
        val deleteQuery = "DELETE FROM product WHERE id = $id;"
        db.execSQL(deleteQuery)
    }

}