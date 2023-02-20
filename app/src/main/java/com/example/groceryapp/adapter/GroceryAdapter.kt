package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.model.GroceryItem

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
        holder.tvName.text = currentPosition.itemName
        holder.tvRate.text = "${currentPosition.itemPrice}"
        holder.tvQuantity.text = "${currentPosition.itemQuantity}"
        val itemTotal = currentPosition.itemPrice * currentPosition.itemQuantity
        holder.tvTotalAmt.text = "EGP $itemTotal"

        holder.ivDelete.setOnClickListener {
            groceryItemClickInterface.onItemClick(currentPosition)
        }

    }

    interface GroceryItemClickInterface{
        fun onItemClick(groceryItem: GroceryItem)
    }

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName = itemView.findViewById<TextView>(R.id.TVItemName)
        val tvRate = itemView.findViewById<TextView>(R.id.TVItemRate)
        val tvQuantity = itemView.findViewById<TextView>(R.id.TVItemQuantity)
        val tvTotalAmt = itemView.findViewById<TextView>(R.id.TVTotalAmt)
        val ivDelete = itemView.findViewById<ImageView>(R.id.IVDelete)

    }
}