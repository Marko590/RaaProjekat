package com.marko590.raaprojekat.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R

import com.marko590.raaprojekat.databinding.FragmentProfileBinding


class ProfileFragment :Fragment(){
    private var _binding: FragmentProfileBinding? = null
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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
        fillFields()


    }

    private fun fillFields(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.textFieldFirstName.editText!!.setText(sharedPref!!.getString(getString(R.string.firstNameKey), ""))
        binding.textFieldLastName.editText!!.setText(sharedPref.getString(getString(R.string.lastNameKey), ""))
        binding.textFieldEmail.editText!!.setText(sharedPref.getString(getString(R.string.emailKey), ""))
        binding.textFieldCuisine.editText!!.setText(sharedPref.getString(getString(R.string.preferredCuisineKey), ""))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}