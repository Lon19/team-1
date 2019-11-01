package com.example.clicsargent.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clicsargent.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private lateinit var databinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding =  FragmentMainBinding.inflate(inflater)

        return databinding.root
    }
}