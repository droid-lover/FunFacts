package com.sachin.funfacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.sachin.funfacts.R
import dagger.hilt.android.AndroidEntryPoint
/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}