package com.marko590.raaprojekat.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentRegisterBinding
import com.marko590.raaprojekat.model.database.entities.UserTable
import com.marko590.raaprojekat.viewmodel.LoginViewModel

class RegisterFragment :Fragment(){
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupBars()

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = resources.getStringArray(R.array.cuisines)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autoCompleteText.setAdapter(adapter)

        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            evaluateFields()
        }
    }


    private fun evaluateFields(){
        val shakeAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
        if(checkNames(binding.textFieldName1,binding.textFieldName2)) {
            if (checkEmails(binding.textFieldEmail, binding.textFieldConfirmEmail)) {

                if (checkPasswords(binding.textFieldPassword,binding.textFieldConfirmPassword)) {

                    viewModel.allUsers.observe(viewLifecycleOwner) { updated ->
                        val userInDb =
                            updated.find { it.email == binding.textFieldEmail.editText!!.text.toString() }
                        if(binding.cuisinePicker.editText!!.text.isBlank()){
                            showError(getString(R.string.errorTextCuisine))
                            binding.cuisinePicker.startAnimation(shakeAnim)
                        }
                        else{
                            if (userInDb != null) {
                                showError("User already in database")
                                binding.textFieldEmail.startAnimation(shakeAnim)
                            } else {
                                insertUser()
                                setCurrentUser()
                                findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                            }
                        }
                    }
                } else {
                    showError(getString(R.string.errorTextPassword))
                    binding.textFieldPassword.startAnimation(shakeAnim)
                    binding.textFieldConfirmPassword.startAnimation(shakeAnim)

                }
            } else {

                binding.textFieldEmail.startAnimation(shakeAnim)
                binding.textFieldConfirmEmail.startAnimation(shakeAnim)
                showError(getString(R.string.errorTextEmail))
            }
        }
        else{
            binding.textFieldName1.startAnimation(shakeAnim)
            binding.textFieldName1.startAnimation(shakeAnim)
            showError(getString(R.string.errorTextNames))
        }
    }


    private fun setCurrentUser(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with (sharedPref!!.edit()) {
            putString(getString(R.string.emailKey),binding.textFieldEmail.editText!!.text.toString())
            putString(getString(R.string.firstNameKey),binding.textFieldName1.editText!!.text.toString())
            putString(getString(R.string.lastNameKey),binding.textFieldName2.editText!!.text.toString())
            putString(getString(R.string.preferredCuisineKey), binding.cuisinePicker.editText!!.text.toString())
            apply()
        }
    }

    private fun setupBars(){
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor= resources.getColor(R.color.statusBarColor)
    }
    private fun showError(errorText:String){
        binding.errorText.text=errorText
        binding.errorText.visibility = View.VISIBLE
    }

    private fun insertUser(){

        val firstName=binding.textFieldName1.editText!!.text.toString()
        val lastName=binding.textFieldName2.editText!!.text.toString()
        val emailName=binding.textFieldEmail.editText!!.text.toString()
        val password=binding.textFieldPassword.editText!!.text.toString()
        val preferredCuisine=binding.cuisinePicker.editText!!.text.toString()

        viewModel.addUser(UserTable(firstName,lastName, emailName,password, preferredCuisine))
    }

    private fun checkEmails(email:TextInputLayout, confirmEmail:TextInputLayout):Boolean{
        return checkEmailFormat(email) and checkFieldsEqual(email,confirmEmail)
    }

    private fun checkNames(firstName: TextInputLayout, secondName:TextInputLayout):Boolean{
        return !(firstName.editText!!.text.isBlank() or secondName.editText!!.text.isBlank())
    }

    private fun checkEmailFormat(email: TextInputLayout):Boolean{
        return if(email.editText!!.text.isBlank()){
            false
        } else{
            android.util.Patterns.EMAIL_ADDRESS.matcher(email.editText!!.text).matches()
        }
    }
    private fun checkFieldsEqual(email:TextInputLayout, confirmEmail:TextInputLayout):Boolean{

        return email.editText!!.text.toString()==confirmEmail.editText!!.text.toString()
    }

    private fun checkPasswords(password:TextInputLayout, passwordConfirm:TextInputLayout):Boolean{
        return if(password.editText!!.text.toString()==passwordConfirm.editText!!.text.toString()){
            if(password.editText!!.text.toString().isNotBlank()) {
                true
            } else{
                binding.errorText.text="Password is blank"
                false
            }
        } else{
            binding.errorText.text="Passwords don't match."
            false
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}