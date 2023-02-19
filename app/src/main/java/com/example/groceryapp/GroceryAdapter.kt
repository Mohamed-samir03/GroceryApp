package com.example.groceryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grocery_rv_item.view.*

class GroceryAdapter(var list: List<GroceryItem>, val groceryItemClickInterface: GroceryItemClickInterface) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        var currentPosition = list[position]
        holder.itemView.TVItemName.text = currentPosition.itemName
        holder.itemView.TVItemRate.text = "${currentPosition.itemPrice}"
        holder.itemView.TVItemQuantity.text = "${currentPosition.itemQuantity}"
        val itemTotal = currentPosition.itemPrice * currentPosition.itemQuantity
        holder.itemView.TVTotalAmt.text = "RS. $itemTotal"

        holder.itemView.IVDelete.setOnClickListener {
            groceryItemClickInterface.onItemClick(currentPosition)
        }

    }

    interface GroceryItemClickInterface{
        fun onItemClick(groceryItem: GroceryItem)
    }

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}