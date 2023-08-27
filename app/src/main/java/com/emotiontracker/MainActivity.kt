package com.emotiontracker

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emotiontracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ChoiceFragment.Callbacks, NoteFragment.Callbacks {

    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val choiceFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (choiceFragment == null) {
            val fragment = ChoiceFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }

    override fun onEmotionSelected(emotionId: Int) {
        val fragment = NoteFragment.newInstance(emotionId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("choice")
            .commit()
    }

    override fun onSaveNoteSelected() {
        val fragment = CalendarFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("note")
            .commit()
    }
}