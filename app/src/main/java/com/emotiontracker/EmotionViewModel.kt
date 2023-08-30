package com.emotiontracker

import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class EmotionViewModel : ViewModel() {

    var date: Date = Calendar.getInstance().apply {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time

    var name: String? = null
    var currentEmotion: Emotion? = null
    //var currentEmotionName = currentEmotion::class.simpleName
    var note: String? = ""

    private val emotionRepository = EmotionRepository.get()

    val moodListLiveData = emotionRepository.getMoods()

    fun addMood(mood: Mood) {
        emotionRepository.addMood(mood)
    }
}
