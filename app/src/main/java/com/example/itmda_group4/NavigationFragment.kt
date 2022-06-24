package com.example.itmda_group4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NavigationFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_navigation, container, false)
        view.findViewById<Button>(R.id.btn_shop).setOnClickListener{
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(StoreFragment(),false)
        }
        view.findViewById<Button>(R.id.btn_cart).setOnClickListener{
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(CartFragment(),false)
        }
        view.findViewById<Button>(R.id.btn_account).setOnClickListener{
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(AccountFragment(),false)
        }
        view.findViewById<Button>(R.id.btn_log_out).setOnClickListener{
            Firebase.auth.signOut()
            var navLogin = activity as FragmentNavigation
            navLogin.navigateFrag(LoginFragment(), addToStack = false)
        }
        return view
    }

}
