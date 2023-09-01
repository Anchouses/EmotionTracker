package com.emotiontracker

import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class NoteViewModel: ViewModel()  {  //NavigateToSomeFragment

    var note: String? = ""

    var date: Date = Calendar.getInstance().apply {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time

    private val emotionRepository = EmotionRepository.get()

    fun addMood(mood: Mood) {
        emotionRepository.addMood(mood)
    }

    var navigator: NavigateToSomeFragment? = null

    fun navigateNoteViewModel(fragmentNavigator: FragmentNavigator) {
        this.navigator = fragmentNavigator
    }

}