package com.marko590.raaprojekat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marko590.raaprojekat.R

import com.marko590.raaprojekat.databinding.FragmentMainBinding

class MainFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceFragment(HomeFragment())
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

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager= parentFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(com.google.android.material.R.anim.m3_motion_fade_enter,com.google.android.material.R.anim.m3_motion_fade_exit)
        fragmentTransaction.replace(R.id.frame_layout,fragment)

        fragmentTransaction.commit()
    }
}