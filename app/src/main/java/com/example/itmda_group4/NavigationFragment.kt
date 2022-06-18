package com.example.itmda_group4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

class NavigationFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.cart).setOnClickListener(this)
        view.findViewById<Button>(R.id.account).setOnClickListener(this)
        view.findViewById<Button>(R.id.shop).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cart -> navController!!.navigate(R.id.action_navigationFragment2_to_cartFragment)
            R.id.account -> navController!!.navigate(R.id.action_navigationFragment2_to_accountFragment)
            R.id.shop -> navController!!.navigate(R.id.action_navigationFragment2_to_storeFragment)
        }
    }
}
