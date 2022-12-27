package com.marko590.raaprojekat.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.marko590.raaprojekat.R

import com.marko590.raaprojekat.databinding.FragmentMainBinding

class MainFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(HomeFragment())
        setupStatusBar()
        setupNavBar()
    }


    private fun setupNavBar(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home->replaceFragment(HomeFragment())
                R.id.profile->replaceFragment(ProfileFragment())
                R.id.favorites->replaceFragment(FavoritesFragment())
                else ->{
                }
            }
            true
        }
    }

    private fun setupStatusBar(){
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor= resources.getColor(R.color.statusBarColor)
        window.navigationBarColor= resources.getColor(R.color.navBarColor)
        window.navigationBarDividerColor= resources.getColor(R.color.navBarColor)
    }

    @SuppressLint("PrivateResource")
    private fun replaceFragment(fragment:Fragment){
        val fragmentManager= parentFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(com.google.android.material.R.anim.m3_motion_fade_enter,com.google.android.material.R.anim.m3_motion_fade_exit)
        fragmentTransaction.replace(R.id.frame_layout,fragment)

        fragmentTransaction.commit()
    }
}