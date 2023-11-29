package com.example.testexercise.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.testexercise.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val authorizationFragment: Fragment = RegistrationFragment()
        fragmentTransaction.replace(R.id.main_container, authorizationFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}