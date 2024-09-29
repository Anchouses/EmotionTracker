package com.emotiontracker.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()  {

    private lateinit var  binding: ActivityMainBinding
    private val fragmentNavigator = FragmentNavigator(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentNavigator.showChoiceFragment()
    }

}