package com.marko590.raaprojekat.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentDetailsBinding
import com.marko590.raaprojekat.model.models.restaurants.DetailArguments
import com.marko590.raaprojekat.viewmodel.DetailsViewModel
import java.math.RoundingMode
import java.text.DecimalFormat


class DetailsFragment :Fragment(){

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by activityViewModels()
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

        setupChipGroup(passedArguments.latitude!!,passedArguments.longitude!!,passedArguments.name!!,passedArguments.snippet!!)
        binding.textView.text=passedArguments.snippet
        viewModel.getUpdatedAddress("json",passedArguments.latitude!!,passedArguments.longitude!!,18,1)

        viewModel.addressLiveData.observe(viewLifecycleOwner){updated->
            binding.address.text=updated.address?.road
            binding.suburb.text=updated.address?.suburb

        }
        binding.website.text=passedArguments.website
        binding.website.movementMethod=LinkMovementMethod.getInstance()
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
        val website=requireArguments().getString("website")
        return DetailArguments(name,snippet,imageUrl,score,latitude,longitude,website)
    }

    private fun setupChipGroup(lat:Double,lng:Double,name:String,snippet:String) {
        binding.chipGroup.setOnCheckedStateChangeListener { chipGroup, _ ->
            for (i in 0 until chipGroup.childCount) {

                if (i == 0) {
                    val chip = chipGroup.getChildAt(i)
                    chip.isClickable = chip.id != chipGroup.checkedChipId
                    chip.setOnClickListener {
                        binding.textView.text =snippet
                    }
                }
                if (i == 1) {
                    val chip = chipGroup.getChildAt(i)
                    chip.isClickable = chip.id != chipGroup.checkedChipId
                    chip.setOnClickListener {
                        viewModel.addressLiveData.observe(viewLifecycleOwner){updated->
                            binding.textView.text=updated.displayName

                        }
                    }

                }
                if (i == 2) {
                    val chip = chipGroup.getChildAt(i)
                    chip.isClickable = chip.id != chipGroup.checkedChipId
                    chip.setOnClickListener {
                        val uriString = "http://maps.google.com/maps?q=$lat,$lng($name)&z=15"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
                        requireContext().startActivity(intent)
                    }
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


    @SuppressLint("SetTextI18n")
    private fun setupRating(score:Double?){
        binding.rBar.rating= score!!.toFloat()
        val df = DecimalFormat("#.####")
        df.roundingMode= RoundingMode.DOWN
        val roundoff = df.format(score)
        binding.textView2.text= "($roundoff)"
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

