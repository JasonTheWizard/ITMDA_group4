package com.example.itmda_group4

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        email = view.findViewById(R.id.log_email)
        password = view.findViewById(R.id.log_password)
        fAuth = Firebase.auth

        view.findViewById<Button>(R.id.btn_register).setOnClickListener{
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(RegisterFragment(),false)
        }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener{
            validateForm()
        }
        return view
    }
    private fun firebaseSignIn(){
        fAuth.signInWithEmailAndPassword(email.text.toString(),
            password.text.toString()).addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    var navHome = activity as FragmentNavigation
                    navHome.navigateFrag(NavigationFragment(), addToStack = true)
                }else{
                    Toast.makeText(context,task.exception?.message,Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun validateForm(){
        when{
            TextUtils.isEmpty(email.text.toString().trim())->{
                email.setError("Please enter username")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Please enter password")
            }
            email.toString().isNotEmpty() &&
                    password.toString().isNotEmpty() ->
            {
                if (email.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    firebaseSignIn()
                }else{
                    email.setError("Please enter valid email")
                }
            }
        }
    }
}
