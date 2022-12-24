package com.marko590.raaprojekat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marko590.raaprojekat.R

class ArticleAdapter(var content:ArrayList<String>):
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val scoreTextView: TextView
        init{
            scoreTextView=view.findViewById(R.id.restaurantName)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_dessert,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.scoreTextView.text=content[position]
    }

    override fun getItemCount(): Int {
        return content.size
    }

}