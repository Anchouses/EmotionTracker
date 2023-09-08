package com.emotiontracker.presentation.note

import androidx.lifecycle.ViewModel
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.domain.EmotionInteractor
import com.emotiontracker.presentation.datasource.Emotion
import java.util.Calendar
import java.util.Date

class NoteViewModel(private var emotionInteractor: EmotionInteractor): ViewModel() {

    private var fragmentNavigator: FragmentNavigator? = null

    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.fragmentNavigator = fragmentNavigator
    }

    var emotionClassName: String? = ""

    var emotionClass: Emotion? = null

    var emotionName: String? = ""

    var note: String? = ""

    var date: Date = Calendar.getInstance().time

    fun saveEmotion(className: String?, note: String?, date: Date) {
        emotionInteractor.saveEmotion(className, note, date)
    }

    fun onForward(){
        fragmentNavigator?.showCalendarFragment()
    }

    fun onButtonBack(){
        fragmentNavigator?.showChoiceFragment()
    }
}
