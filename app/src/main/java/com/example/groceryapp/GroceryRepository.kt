package com.example.groceryapp

class GroceryRepository(private val db: GroceryDatabase) {

    suspend fun insert(item: GroceryItem) = db.getGroceryDao().insert(item)
    suspend fun delete(item: GroceryItem) = db.getGroceryDao().delete(item)

    fun allGroceryItems() = db.getGroceryDao().getAllGroceryItems()

}