package com.emotiontracker


import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class ChoiceViewModel : ViewModel() {

    var date: Date = Calendar.getInstance().time

    var currentEmotion: Emotion? = null

    var navigator: NavigateToSomeFragment? = null

    fun navigateChoiceViewModel(fragmentNavigator: FragmentNavigator){
        this.navigator = fragmentNavigator
    }
}
