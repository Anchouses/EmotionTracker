package com.emotiontracker.presentation.choice


import androidx.lifecycle.ViewModel
import com.emotiontracker.presentation.datasource.Emotion
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.presentation.navigation.NavigateToSomeFragment
import java.util.Calendar
import java.util.Date

class ChoiceViewModel : ViewModel() {

    var date: Date = Calendar.getInstance().time

    var currentEmotion: Emotion? = null

    var navigator: NavigateToSomeFragment? = null

    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.navigator = fragmentNavigator
    }

    fun onForward(emotionClassName: String){
        navigator?.showNoteFragment(emotionClassName)
    }
}
