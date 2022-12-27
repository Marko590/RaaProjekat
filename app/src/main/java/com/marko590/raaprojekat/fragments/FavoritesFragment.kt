package com.marko590.raaprojekat.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.adapter.FavoritesAdapter
import com.marko590.raaprojekat.databinding.FragmentFavoritesBinding
import com.marko590.raaprojekat.model.models.restaurants.Results
import com.marko590.raaprojekat.viewmodel.FavoritesViewModel

class FavoritesFragment :Fragment(){
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by activityViewModels()

    private var dataset:ArrayList<Results> = arrayListOf()
    override fun onCreateView(
    inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val cuisine=sharedPref!!.getString(getString(R.string.preferredCuisineKey), "")!!
        viewModel.getUpdatedFavorites("Paris", cuisine)
        setupRecycleView()
    }

    private fun setupRecycleView(){
        val adapter = FavoritesAdapter(dataset)
        viewModel.uiTextLiveData.observe(viewLifecycleOwner) { updatedActivity ->
            adapter.updateUserList(updatedActivity.results)
            if (updatedActivity.results.isNullOrEmpty()){
                binding.recyclerView.layoutParams.width= ConstraintLayout.LayoutParams.MATCH_PARENT
                binding.recyclerView.layoutParams.height=900
                binding.errorMessage.visibility=View.VISIBLE
            }
            else{
                binding.recyclerView.layoutParams.width= ConstraintLayout.LayoutParams.MATCH_PARENT
                binding.recyclerView.layoutParams.height=ConstraintLayout.LayoutParams.MATCH_PARENT
                binding.errorMessage.visibility=View.INVISIBLE
            }
        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}