package com.marko590.raaprojekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentHomeBinding
import com.marko590.raaprojekat.viewmodel.BoredViewModel



class HomeFragment :Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val viewModel: BoredViewModel by activityViewModels()

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



        fragmentTextUpdateObserver()
        fillBundle()
        viewModel.getUpdatedText()
        binding.button2.setOnClickListener{
                viewModel.getUpdatedText()
        }
        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment,bundle)
        }
    }

    private fun fragmentTextUpdateObserver() {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner) { updatedActivity ->
            binding.activity.text = updatedActivity.activity
            binding.participantNumber.text = updatedActivity.participants.toString()
            binding.price.text = updatedActivity.price.toString()

        }
    }
    private fun fillBundle() {

        viewModel.uiTextLiveData.observe(viewLifecycleOwner) { updatedActivity ->
            bundle.putString("activity", updatedActivity.activity)
            bundle.putString("link", updatedActivity.link)
            bundle.putString("type", updatedActivity.type)
            bundle.putFloat("accessibility", updatedActivity.accessibility)
            bundle.putFloat("price", updatedActivity.price)
            bundle.putInt("key", updatedActivity.key)
            bundle.putInt("participants", updatedActivity.participants)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}