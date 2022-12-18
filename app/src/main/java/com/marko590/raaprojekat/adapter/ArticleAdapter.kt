package com.marko590.raaprojekat.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.marko590.raaprojekat.R

class ArticleAdapter(var content:ArrayList<String>):
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val linearLayout:LinearLayout
        val scoreTextView: TextView
        val firstLetterTextView: TextView
        init{
            linearLayout=view.findViewById(R.id.layout)
            firstLetterTextView=view.findViewById(R.id.txt_desc)
            scoreTextView=view.findViewById(R.id.txt_name)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_dessert,parent,false)



        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val score="Article on position number "+position.toString()
        holder.firstLetterTextView.text=('A'+position).toString()
        holder.scoreTextView.text=content[position]
    }

    override fun getItemCount(): Int {
        return content.size;
    }


}