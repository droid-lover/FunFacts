package com.sachin.funfacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.sachin.funfacts.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //goToLogin()
    }

    private fun goToLogin() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
    }
}