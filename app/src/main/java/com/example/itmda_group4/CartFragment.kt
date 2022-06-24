package com.example.itmda_group4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class CartFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_cart, container, false)
        view.findViewById<Button>(R.id.btn_cart_back).setOnClickListener{
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(NavigationFragment(),false)
        }
        return view
    }
}