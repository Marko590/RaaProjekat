package com.marko590.raaprojekat.fragments

import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.adapter.DessertAdapter
import com.marko590.raaprojekat.databinding.FragmentDetailsBinding
import com.marko590.raaprojekat.models.ApiActivity


class DetailsFragment :Fragment(){
    private var dessertAdapter: DessertAdapter? = null
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

        requireActivity().setActionBar(binding.toolbar)

        requireActivity().actionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().actionBar!!.setDisplayShowHomeEnabled(true)


        binding.toolbar.title=passedArguments.activity
        binding.toolbar.setNavigationOnClickListener(){
            findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
        }

        binding.favouriteFab.setOnClickListener(){
            Toast.makeText(
                requireContext(), "Added to favourites!",
                Toast.LENGTH_SHORT
            ).show()
        }
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