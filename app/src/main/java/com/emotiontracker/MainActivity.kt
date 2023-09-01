package com.emotiontracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emotiontracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  { //ChoiceFragment.Callbacks, NoteFragment.Callbacks

    private lateinit var  binding: ActivityMainBinding
    private val fragmentNavigator = FragmentNavigator(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentNavigator.showChoiceFragment()
    }

//    override fun onEmotionSelected(emotionClassName: String) {
//        fragmentNavigator.showNoteFragment(emotionClassName)
//    }

//    override fun onSaveNoteSelected() {
//        fragmentNavigator.showCalendarFragment()
//    }
}