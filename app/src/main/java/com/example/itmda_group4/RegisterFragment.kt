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

class RegisterFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var cnfPassword: EditText
    private lateinit var fAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        email = view.findViewById(R.id.reg_email)
        username = view.findViewById(R.id.reg_username)
        password = view.findViewById(R.id.reg_password)
        cnfPassword = view.findViewById(R.id.reg_cnf_password)
        fAuth = Firebase.auth

        view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener{
            var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(),false)
        }
        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener{
            validateEmptyForm()
        }
        return view
    }
    private fun firebaseSignUp(){
        fAuth.createUserWithEmailAndPassword(email.text.toString(),
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

    private fun validateEmptyForm(){
        when{
            TextUtils.isEmpty(email.text.toString().trim())->{
                email.setError("Please enter email")
            }
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Please enter username")
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Please enter password")
            }
            TextUtils.isEmpty(cnfPassword.text.toString().trim())->{
                cnfPassword.setError("Please re-enter above password")
            }

            email.text.toString().isNotEmpty() &&
                    username.toString().isNotEmpty() &&
                    password.toString().isNotEmpty() &&
                    cnfPassword.toString().isNotEmpty() ->
            {
                if (email.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if(password.text.toString().length>=5){
                        if(password.text.toString() == cnfPassword.text.toString()){
                            firebaseSignUp()
                                Toast.makeText(context,"Successfully registered",Toast.LENGTH_SHORT).show()
                        }else{
                            cnfPassword.setError("Passwords did not match")
                        }
                    }else{
                        password.setError("5 or more character passwords")
                    }
                }else{
                    email.setError("Please enter valid email")
                }
            }
        }
    }
}