package com.emotiontracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.emotiontracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ChoiceFragment.Callbacks {

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val choiceFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (choiceFragment == null) {
            val fragment = ChoiceFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }

    override fun onEmotionSelected(){
        val fragment = NoteFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

}