package com.example.clicsargent.main_fragment

import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController

@BindingAdapter("onClickNavigateToOptionFragment")
fun setOnClickListener(view: View,@IdRes destination: Int){
    view.setOnClickListener {
        view.findNavController().navigate(destination)
    }
}