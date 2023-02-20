package com.example.groceryapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.repo.GroceryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository): ViewModel() {

    // In coroutines thread insert item in insert function.
    fun insert(item: GroceryItem) = GlobalScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(item: GroceryItem) = GlobalScope.launch {
        repository.delete(item)
    }

    //Here we initialized allGroceryItems function with repository
    fun allGroceryItems() = repository.allGroceryItems()

}