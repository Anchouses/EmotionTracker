package com.emotiontracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), ChoiceFragment.Callbacks {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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