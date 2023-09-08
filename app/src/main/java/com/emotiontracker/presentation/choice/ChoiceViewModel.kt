package com.emotiontracker.presentation.choice


import androidx.lifecycle.ViewModel
import com.emotiontracker.presentation.datasource.Emotion
import com.emotiontracker.presentation.navigation.FragmentNavigator
import java.util.Calendar
import java.util.Date

class ChoiceViewModel(private var fragmentNavigator: FragmentNavigator) : ViewModel() {

    var date: Date = Calendar.getInstance().time

    var currentEmotion: Emotion? = null

    fun onForward(emotionClassName: String){
        fragmentNavigator.showNoteFragment(emotionClassName)
    }

    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.fragmentNavigator = fragmentNavigator
    }
}
