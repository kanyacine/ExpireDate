package com.example.expiredate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GTINListAdapter(val data : List<GTINModel>) : RecyclerView.Adapter<GTINListAdapter.ViewHolder>(){

    class ViewHolder(rowView: View) : RecyclerView.ViewHolder(rowView)
    {
        val gtinTextView : TextView = rowView.findViewById(R.id.item_txt_gtin)
        val dateTextView : TextView = rowView.findViewById(R.id.item_txt_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowView : View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gtin_date,
            parent,
            false
        )
        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gtinTextView.text = data[position].GTIN
        holder.dateTextView.text = data[position].Date
    }

    override fun getItemCount(): Int {
        return data.size
    }

}