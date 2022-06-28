package com.example.itmda_group4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_cart.*

class MainActivity : AppCompatActivity(), FragmentNavigation {
    private lateinit var fAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fAuth = Firebase.auth

        val currentUser = fAuth.currentUser
        if(currentUser != null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container,NavigationFragment()).addToBackStack(null)
                .commit()
        }else{
            supportFragmentManager.beginTransaction()
                .add(R.id.container,LoginFragment())
                .commit()
        }

    }

    class MainActivity : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_cart)

            var increment_number = 0;

            add_bread.setOnClickListener{

                increment_number++

                increment_bread.text = increment_number.toString()

            }

            remove_bread.setOnClickListener {

                increment_number--

                increment_bread.text = increment_number.toString()
            }

        }
    }

    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment)

        if(addToStack){
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}