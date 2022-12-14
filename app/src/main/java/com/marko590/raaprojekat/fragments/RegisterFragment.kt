package com.marko590.raaprojekat.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentRegisterBinding

class RegisterFragment :Fragment(){
    private var _binding: FragmentRegisterBinding? = null
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.loginLink.text=sharedPref!!.getString("markobojkovicmb@gmail.com","")
        binding.registerButton.setOnClickListener {
            if(checkEmails(binding.textFieldEmail,binding.textFieldConfirmEmail)){

                if(checkPasswords(binding.textFieldPassword,binding.textFieldConfirmPassword)) {
                    var storedPassword =
                        sharedPref?.getString(binding.textFieldEmail.editText!!.text.toString(), "")

                    if (storedPassword == "") {
                        with(sharedPref!!.edit()) {
                            putString(
                                binding.textFieldEmail.editText!!.text.toString(),
                                binding.textFieldPassword.editText!!.text.toString()
                            )
                            apply()
                            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                        }

                    } else {
                        binding.errorText.text = "User already registered."
                        binding.errorText.visibility=View.VISIBLE
                    }
                }
                else{
                    var shakeAnim= AnimationUtils.loadAnimation(requireContext(),R.anim.shake)
                    binding.textFieldPassword.startAnimation(shakeAnim)
                    binding.textFieldConfirmPassword.startAnimation(shakeAnim)
                    binding.errorText.visibility=View.VISIBLE
                }
            }
            else{
                var shakeAnim= AnimationUtils.loadAnimation(requireContext(),R.anim.shake)
                binding.textFieldEmail.startAnimation(shakeAnim)
                binding.textFieldConfirmEmail.startAnimation(shakeAnim)
                binding.errorText.text="invalid email"
                binding.errorText.visibility=View.VISIBLE
            }

        }
    }


    fun checkEmails(email:TextInputLayout,confirmEmail:TextInputLayout):Boolean{
        return checkEmailFormat(email) and checkFieldsEqual(email,confirmEmail)
    }

    fun checkEmailFormat(email: TextInputLayout):Boolean{
        if(email.editText!!.text.isBlank()){
            return false
        }
        else{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email.editText!!.text).matches()
        }
    }
    fun checkFieldsEqual(email:TextInputLayout, confirmEmail:TextInputLayout):Boolean{
        binding.errorText.text="invalid1"
        return email.editText!!.text.toString()==confirmEmail.editText!!.text.toString()
    }

    fun checkPasswords(password:TextInputLayout,passwordConfirm:TextInputLayout):Boolean{
        if(password.editText!!.text.toString()==passwordConfirm.editText!!.text.toString()){
            if(!password.editText!!.text.toString().isBlank()) {
                return true
            }
            else{
                binding.errorText.text="Password is blank"
                return false
            }
        }
        else{
            binding.errorText.text="Passwords don't match."
            return false
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}