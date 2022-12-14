package com.marko590.raaprojekat.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentLoginBinding

class LoginFragment :Fragment(){
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
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


        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            if(checkEmail()) {

                if(checkLogin()){
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
                else{
                    binding.passwordError.text="Invalid E-Mail/Password combination,try again."
                    binding.passwordError.visibility=View.VISIBLE
                }
            }
            else{
                var shakeAnim= AnimationUtils.loadAnimation(requireContext(),R.anim.shake)
                binding.textFieldEmail.startAnimation(shakeAnim)
                binding.emailError.visibility=View.VISIBLE
                Toast.makeText(
                    requireContext(), "Invalid E-Mail!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }



        binding.registerLink.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }
    fun checkLogin():Boolean{
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val storedPassword=sharedPref!!.getString(binding.textFieldEmail.editText!!.text.toString(),"")
        if(storedPassword==""){
            binding.passwordError.text="Invalid E-Mail/Password combination,try again."
            binding.passwordError.visibility=View.VISIBLE
            return false
        }
        else{
            if(storedPassword==binding.textFieldPassword.editText!!.text.toString()){
                return true
            }
        }
        return false
    }
    fun checkEmail():Boolean{
        if(binding.textFieldEmail.editText!!.text.isBlank()){
            return false
        }
        else{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(binding.textFieldEmail.editText!!.text).matches()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}