package com.marko590.raaprojekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko590.raaprojekat.adapter.ArticleAdapter
import com.marko590.raaprojekat.adapter.SwipeActionLeft
import com.marko590.raaprojekat.adapter.SwipeActionRight
import com.marko590.raaprojekat.databinding.FragmentFavoritesBinding

class FavoritesFragment :Fragment(){
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private var content:ArrayList<String> =arrayListOf("Marc","Donald","Bruh","Jessy","Amber","Walter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
    inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var adapter=ArticleAdapter(content)
        var itemTouchHelperLeft= ItemTouchHelper(SwipeActionLeft(adapter))
        var itemTouchHelperRight= ItemTouchHelper(SwipeActionRight(adapter))
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext()!!)
        itemTouchHelperLeft.attachToRecyclerView(binding.recyclerView)
        itemTouchHelperRight.attachToRecyclerView(binding.recyclerView)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}