package com.marko590.raaprojekat.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentDetailsBinding
import com.marko590.raaprojekat.model.models.DetailArguments


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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val passedArguments=unpackBundle()
        setupActionBar()
        setupRating(requireArguments().getDouble("score"))
        setupTitle(requireArguments().getString("name"))
        setupFavFab()

        setupChipGroup(passedArguments.latitude!!,passedArguments.longitude!!,passedArguments.name!!)
        binding.textView.text=passedArguments.snippet

        Glide.with(this).load(requireArguments().getString("link")).into(binding.mainImage).waitForLayout()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
        }

    }

    private fun unpackBundle(): DetailArguments {
        val name=requireArguments().getString("name")
        val snippet=requireArguments().getString("snippet")
        val imageUrl=requireArguments().getString("link")
        val score=requireArguments().getDouble("score")
        val latitude=requireArguments().getDouble("latitude")
        val longitude=requireArguments().getDouble("longitude")
        return DetailArguments(name,snippet,imageUrl,score,latitude,longitude)
    }

    private fun setupChipGroup(lat:Double,lng:Double,name:String){
        binding.chipGroup.setOnCheckedStateChangeListener{ chipGroup, _ ->
            for (i in 0 until chipGroup.childCount){
                val chip = chipGroup.getChildAt(i)
                chip.isClickable = chip.id != chipGroup.checkedChipId
                chip.setOnClickListener {
                    binding.textView.text=i.toString()
                    val uriString = "http://maps.google.com/maps?q=$lat,$lng($name)&z=15"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
                    requireContext().startActivity(intent)
                }
            }

        }
    }
    private fun setupFavFab(){
        binding.favouriteFab.setOnClickListener{
            Toast.makeText(
                requireContext(), "Added to favourites!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupTitle(title:String?){
        binding.toolbar.title=title
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

    }


    private fun setupRating(score:Double?){
        binding.rBar.rating= score!!.toFloat()
        val ratingText="(${score.toFloat()}"+")"
        binding.textView2.text=ratingText
    }

    private fun setupActionBar(){
        requireActivity().setActionBar(binding.toolbar)
        requireActivity().actionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().actionBar!!.setDisplayShowHomeEnabled(true)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

