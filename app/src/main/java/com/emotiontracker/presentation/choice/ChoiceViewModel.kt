package com.emotiontracker.presentation.choice


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emotiontracker.presentation.datasource.Emotion
import com.emotiontracker.presentation.navigation.FragmentNavigator
import java.util.Calendar
import java.util.Date

class ChoiceViewModel(private var fragmentNavigator: FragmentNavigator?) : ViewModel() {

    var date: Date = Calendar.getInstance().time

    var currentEmotion: Emotion? = null


    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.fragmentNavigator = fragmentNavigator
    }

    fun onForward(emotionClassName: String){
        fragmentNavigator?.showNoteFragment(emotionClassName)
    }

companion object{
    val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            val fragmentNavigator = FragmentNavigator(activity = AppCompatActivity())

            return ChoiceViewModel(fragmentNavigator) as T
        }
    }
}
}
