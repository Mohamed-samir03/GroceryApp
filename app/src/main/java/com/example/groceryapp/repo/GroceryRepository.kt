package com.example.groceryapp.repo

import com.example.groceryapp.data.GroceryDatabase
import com.example.groceryapp.model.GroceryItem

class GroceryRepository(private val db: GroceryDatabase) {

    suspend fun insert(item: GroceryItem) = db.getGroceryDao().insert(item)
    suspend fun delete(item: GroceryItem) = db.getGroceryDao().delete(item)

    fun allGroceryItems() = db.getGroceryDao().getAllGroceryItems()

}