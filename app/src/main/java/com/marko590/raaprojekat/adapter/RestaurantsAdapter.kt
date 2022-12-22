package com.marko590.raaprojekat.adapter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.model.models.Results
import java.math.RoundingMode
import java.text.DecimalFormat

class RestaurantsAdapter(var dataset:ArrayList<Results>):

    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {
    private var bundle: Bundle =Bundle()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val restaurantName: TextView
        val restaurantRating:TextView
        val location:TextView
        val imageView: ImageView
        val card:CardView
        init{
            location=view.findViewById(R.id.location)
            card=view.findViewById(R.id.cardContainer)
            imageView=view.findViewById(R.id.restaurantImage)
            restaurantRating=view.findViewById(R.id.restaurantRating)
            restaurantName=view.findViewById(R.id.restaurantName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_dessert,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val df = DecimalFormat("#.##")
        df.roundingMode=RoundingMode.DOWN
        val roundoff = df.format(dataset[position].score!!)
        holder.location.text=dataset[position].locationId
        holder.restaurantName.text=dataset[position].name
        holder.restaurantRating.text=roundoff.toString()
        Glide.with(holder.imageView.context)
            .load(dataset[position].images[0].sizes!!.original!!.url)
            .skipMemoryCache(true)//for caching the image url in case phone is offline
            .into(holder.imageView)

        var navController: NavController? = null
        holder.card.setOnClickListener {
            bundle.putString("name",dataset[position].name)
            bundle.putString("snippet",dataset[position].snippet)
            bundle.putString("link", dataset[position].images[0].sizes!!.original!!.url)
            bundle.putDouble("score", dataset[position].score!!)
            bundle.putDouble("latitude", dataset[position].coordinates!!.latitude!!)
            bundle.putDouble("longitude",  dataset[position].coordinates!!.longitude!!)

            navController = Navigation.findNavController(holder.restaurantName)
            navController!!.navigate(R.id.action_mainFragment_to_detailsFragment,bundle)

        }
    }

    fun updateUserList(datasetNew: ArrayList<Results>) {
        dataset.clear()
        dataset = datasetNew

        notifyDataSetChanged()
    }


}