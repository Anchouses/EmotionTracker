package com.emotiontracker.presentation.note

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emotiontracker.data.repository.EmotionRepository
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.domain.EmotionInteractor
import com.emotiontracker.presentation.datasource.Emotion
import java.util.Calendar
import java.util.Date

class NoteViewModel(private var emotionInteractor: EmotionInteractor,
                    private var fragmentNavigator: FragmentNavigator): ViewModel()  {

    var emotionClassName: String? = ""

    var emotionClass: Emotion? = null

    var emotionName: String? = ""

    var note: String? = ""

    var date: Date = Calendar.getInstance().time

    fun initViewModel(fragmentNavigator: FragmentNavigator) {
        this.fragmentNavigator = fragmentNavigator
    }

    fun saveEmotion(className: String?, note: String?, date: Date) {
        emotionInteractor.saveEmotion(className, note, date)
    }
    fun onForward(){
        fragmentNavigator.showCalendarFragment()
    }

    fun onButtonBack(){
        fragmentNavigator.showChoiceFragment()
    }

    companion object {

        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {
                val emotionInterator = EmotionInteractor(EmotionRepository.get())
                val fragmentNavigator = FragmentNavigator(activity = AppCompatActivity())

                return NoteViewModel(emotionInterator, fragmentNavigator) as T
            }
        }
    }

}
