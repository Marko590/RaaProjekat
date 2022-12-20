package com.marko590.raaprojekat.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.ChipGroup
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentDetailsBinding
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

        requireActivity().setActionBar(binding.toolbar)

        requireActivity().actionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().actionBar!!.setDisplayShowHomeEnabled(true)


        binding.toolbar.title=passedArguments.activity
        binding.toolbar.setNavigationOnClickListener(){
            findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
        }


        binding.textView2.text='('+binding.rBar.rating.toString()+')'

        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        binding.favouriteFab.setOnClickListener{
            Toast.makeText(
                requireContext(), "Added to favourites!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            for (i in 0 until chipGroup.childCount){
                val chip = chipGroup.getChildAt(i)
                chip.isClickable = chip.id != chipGroup.checkedChipId
                chip.setOnClickListener(){
                    binding.textView.text=i.toString()
                }
            }
            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
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

