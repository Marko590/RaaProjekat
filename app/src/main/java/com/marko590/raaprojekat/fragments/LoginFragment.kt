package com.marko590.raaprojekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentLoginBinding

class LoginFragment :Fragment(){
    private var _binding: FragmentLoginBinding? = null
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textFieldFirstName.elevation=100f
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        binding.registerLink.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}