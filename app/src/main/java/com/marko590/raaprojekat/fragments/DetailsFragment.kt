package com.marko590.raaprojekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.*
import com.marko590.raaprojekat.models.ApiActivity

class DetailsFragment :Fragment(){
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var retVal= requireArguments().getString("activity_name")

        var passedArguments=unpackBundle()
        binding.restaurantTitle.text=passedArguments.activity
        binding.restaurantName.text=passedArguments.activity
        binding.restaurantAddress.text="Number of participants: "+passedArguments.participants.toString()
        binding.restaurantInfo.text=passedArguments.type
    }

    private fun unpackBundle():ApiActivity{
        var activity=requireArguments().getString("activity");
        var link=requireArguments().getString("link");
        var type=requireArguments().getString("type");
        var accessibility=requireArguments().getFloat("accessibility");
        var price=requireArguments().getFloat("price");
        var key=requireArguments().getInt("key");
        var participants=requireArguments().getInt("participants")

        return ApiActivity(activity!!,type!!,participants,price,link!!,key,accessibility)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}