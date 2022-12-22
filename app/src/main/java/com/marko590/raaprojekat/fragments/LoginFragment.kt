package com.marko590.raaprojekat.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.marko590.raaprojekat.R
import com.marko590.raaprojekat.databinding.FragmentLoginBinding
import com.marko590.raaprojekat.model.database.entities.UserTable
import com.marko590.raaprojekat.model.models.Results
import com.marko590.raaprojekat.viewmodel.LoginViewModel
import com.marko590.raaprojekat.viewmodel.RestaurantViewModel


class LoginFragment :Fragment(){
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val viewModel: LoginViewModel by activityViewModels()

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
            viewModel.allUsers.observe(viewLifecycleOwner) { updated ->
                var userInDb=updated.find { it.email == binding.textFieldEmail.editText!!.text.toString() }
                if (userInDb!= null) {
                    if(userInDb.password==binding.textFieldPassword.editText!!.text.toString()) {
                        setCurrentUser(userInDb.email,userInDb.firstname,userInDb.lastname,userInDb.preferredCuisine)
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }
                    else {
                        shakeFields()
                        showError(getString(R.string.errorCombination))
                    }
                }
                else {
                    shakeFields()
                    showError(getString(R.string.errorCombination))
                }
            }
        }

        binding.registerLink.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        setupBars()


    }

    private fun setCurrentUser(email:String,firstName:String,lastName:String,cuisine:String){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with (sharedPref!!.edit()) {
            putString(getString(R.string.emailKey), email)
            putString(getString(R.string.firstNameKey), firstName)
            putString(getString(R.string.lastNameKey), lastName)
            putString(getString(R.string.preferredCuisineKey), cuisine)
            apply()
        }
    }
    private fun shakeFields(){
        var shakeAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.shake)
        binding.textFieldEmail.startAnimation(shakeAnim)
        binding.textFieldPassword.startAnimation(shakeAnim)
    }

    private fun setupBars(){
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor= resources.getColor(R.color.statusBarColorLogin)
        window.navigationBarColor= resources.getColor(R.color.navBarColorLogin)
    }

    private fun showError(errorText:String){
        binding.passwordError.text = errorText
        binding.passwordError.visibility = View.VISIBLE
    }

    private fun checkEmail():Boolean{
        if(binding.textFieldEmail.editText!!.text.isBlank()){
            return false
        }
        else{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(binding.textFieldEmail.editText!!.text).matches()
        }
    }

    fun Context.getColorFromAttr(
        @AttrRes attrColor: Int,
        typedValue: TypedValue = TypedValue(),
        resolveRefs: Boolean = true
    ): Int {
        theme.resolveAttribute(attrColor, typedValue, resolveRefs)
        return typedValue.data
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}