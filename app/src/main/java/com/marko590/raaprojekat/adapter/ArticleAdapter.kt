package com.marko590.raaprojekat.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.marko590.raaprojekat.MainActivity
import com.marko590.raaprojekat.R

class ArticleAdapter:RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val scoreTextView: TextView
        val firstLetterTextView: TextView
        init{

            firstLetterTextView=view.findViewById(R.id.txt_firstletter)
            scoreTextView=view.findViewById(R.id.txt_name)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_dessert,parent,false)

        view.findViewById<TextView>(R.id.txt_name).setOnClickListener {

        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val score="Article on position number "+position.toString()
        holder.firstLetterTextView.text=('A'+position).toString()
        holder.scoreTextView.text=score.toString()
    }

    override fun getItemCount(): Int {
        return 30;
    }

}