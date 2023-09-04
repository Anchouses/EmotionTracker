package com.emotiontracker.presentation.note

import androidx.lifecycle.ViewModel
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.domain.EmotionInteractor
import com.emotiontracker.presentation.navigation.NavigateToSomeFragment
import java.util.Calendar
import java.util.Date

class NoteViewModel: ViewModel()  {

    var emotionClassName: String? = ""

    var note: String? = ""

    var date: Date = Calendar.getInstance().time

    private val emotionInterator = EmotionInteractor()

    fun saveEmotion() {
        emotionInterator.emotionModel.className = emotionClassName
        emotionInterator.emotionModel.note = note
        emotionInterator.emotionModel.date = date
        emotionInterator.saveEmotion()
    }

    var navigator: NavigateToSomeFragment? = null

    fun initViewModel(fragmentNavigator: FragmentNavigator) {
        this.navigator = fragmentNavigator
    }

    fun onForward(){
        navigator?.showCalendarFragment()
    }

    fun onButtonBack(){
        navigator?.showChoiceFragment()
    }
}
