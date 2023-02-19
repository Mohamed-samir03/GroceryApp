package com.example.groceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),GroceryAdapter.GroceryItemClickInterface{

    lateinit var list: List<GroceryItem>
    lateinit var groceryAdapter: GroceryAdapter
    lateinit var ViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList<GroceryItem>()
        groceryAdapter = GroceryAdapter(list,this)

        RVItems.layoutManager = LinearLayoutManager(this)
        RVItems.adapter = groceryAdapter
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        ViewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        ViewModel.allGroceryItems().observe(this) {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
        }

        FABAdd.setOnClickListener {
            openDialog()
        }


    }

    fun openDialog(){

    }

    override fun onItemClick(groceryItem: GroceryItem) {
        ViewModel.delete(groceryItem)
        Toast.makeText(applicationContext, "Item Deleted...", Toast.LENGTH_SHORT).show()
    }
}