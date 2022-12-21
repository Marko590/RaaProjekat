package com.marko590.raaprojekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.adapter.ArticleAdapter
import com.marko590.raaprojekat.adapter.RestaurantsAdapter
import com.marko590.raaprojekat.adapter.SwipeActionLeft
import com.marko590.raaprojekat.adapter.SwipeActionRight
import com.marko590.raaprojekat.databinding.FragmentHomeBinding
import com.marko590.raaprojekat.model.models.Results
import com.marko590.raaprojekat.viewmodel.BoredViewModel
import com.marko590.raaprojekat.viewmodel.RestaurantViewModel


class HomeFragment :Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val viewModel: RestaurantViewModel by activityViewModels()
    var dataset:ArrayList<Results> = arrayListOf()
    private var bundle:Bundle=Bundle()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener(){
            viewModel.getUpdatedText()
        }
        setupRecycleView()
    }



    private fun setupRecycleView(){
        var adapter = RestaurantsAdapter(dataset)
        viewModel.uiTextLiveData.observe(viewLifecycleOwner) { updatedActivity ->
            adapter.updateUserList(updatedActivity.results)
        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext()!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}