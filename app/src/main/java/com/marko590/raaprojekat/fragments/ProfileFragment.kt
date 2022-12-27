package com.marko590.raaprojekat.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentProfileBinding
import com.marko590.raaprojekat.model.database.entities.UserTable
import com.marko590.raaprojekat.viewmodel.LoginViewModel


class ProfileFragment :Fragment(){
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logoutButton.setOnClickListener{
            viewModel.fetchUsers()
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
        fillFields()
        viewModel.fetchUsers()
        var check=false
        binding.editButton.setOnClickListener{
            check=!check
            changeFieldAvailability(check)
        }

        binding.commitButton.setOnClickListener {
            viewModel.fetchUsers()
            updateUser()
            setCurrentUser()
        }
    }

    private fun updateUser(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        viewModel.updateUser(UserTable(
            binding.textFieldFirstName.editText!!.text.toString(),
            binding.textFieldLastName.editText!!.text.toString(),
            binding.textFieldEmail.editText!!.text.toString(),
            sharedPref!!.getString(resources.getString(R.string.passwordKey),"")!!,
            binding.textFieldCuisine.editText!!.text.toString(),
            sharedPref.getInt(resources.getString(R.string.id),0)
        ))
    }
    private fun setCurrentUser(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with (sharedPref!!.edit()) {
            putString(getString(R.string.emailKey),binding.textFieldEmail.editText!!.text.toString())
            putString(getString(R.string.firstNameKey),binding.textFieldFirstName.editText!!.text.toString())
            putString(getString(R.string.lastNameKey),binding.textFieldLastName.editText!!.text.toString())
            putString(getString(R.string.preferredCuisineKey), binding.textFieldCuisine.editText!!.text.toString())
            apply()
        }
    }

    private fun fillFields(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.textFieldFirstName.editText!!.setText(sharedPref!!.getString(getString(R.string.firstNameKey), ""))
        binding.textFieldLastName.editText!!.setText(sharedPref.getString(getString(R.string.lastNameKey), ""))
        binding.textFieldEmail.editText!!.setText(sharedPref.getString(getString(R.string.emailKey), ""))
        binding.textFieldCuisine.editText!!.setText(sharedPref.getString(getString(R.string.preferredCuisineKey), ""))
    }

    private fun changeFieldAvailability(check:Boolean){
        binding.textFieldFirstName.isEnabled=check
        binding.textFieldLastName.isEnabled=check

        binding.textFieldCuisine.isEnabled=check
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}