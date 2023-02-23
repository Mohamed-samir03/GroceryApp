package com.example.groceryapp.ui

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.viewModel.GroceryViewModel
import com.example.groceryapp.viewModel.GroceryViewModelFactory
import com.example.groceryapp.R
import com.example.groceryapp.adapter.GroceryAdapter
import com.example.groceryapp.db.GroceryDatabase
import com.example.groceryapp.model.GroceryItem
import com.example.groceryapp.repo.GroceryRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryAdapter.GroceryItemClickInterface{

    lateinit var list: List<GroceryItem>
    lateinit var groceryAdapter: GroceryAdapter
    lateinit var ViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList<GroceryItem>()
        groceryAdapter = GroceryAdapter(list,this)

        val rvItem = findViewById<RecyclerView>(R.id.RVItems)
        val addBtn = findViewById<FloatingActionButton>(R.id.FABAdd)

        rvItem.layoutManager = LinearLayoutManager(this)
        rvItem.adapter = groceryAdapter
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        ViewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        ViewModel.allGroceryItems().observe(this) {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
        }

        addBtn.setOnClickListener {
            openDialog()
        }


    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)

        val cancel = dialog.findViewById<Button>(R.id.btnCancel)
        val add = dialog.findViewById<Button>(R.id.btnAdd)

        cancel.setOnClickListener {
            dialog.dismiss()
        }

        add.setOnClickListener {
            val etName = dialog.findViewById<EditText>(R.id.ETItemName)
            val etQuantity = dialog.findViewById<EditText>(R.id.ETItemQuantity)
            val etPrice = dialog.findViewById<EditText>(R.id.ETItemPrice)
            val name = etName.text.toString()
            val quantity = etQuantity.text.toString()
            val price  = etPrice.text.toString()
            if(name.isNotEmpty() && price.isNotEmpty() && quantity.isNotEmpty()){
                val items = GroceryItem(name,quantity.toInt(),price.toInt())
                ViewModel.insert(items)
                Toast.makeText(this, "Item Inserted..", Toast.LENGTH_SHORT).show()
                groceryAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(this, "Please Enter All Data", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()

    }

    override fun onItemClick(groceryItem: GroceryItem) {
        ViewModel.delete(groceryItem)
        Toast.makeText(applicationContext, "Item Deleted...", Toast.LENGTH_SHORT).show()
    }
}