package com.emotiontracker

import androidx.lifecycle.ViewModel

class CalendarViewModel: ViewModel() {

    private val emotionRepository = EmotionRepository.get()

    val moodListLiveData = emotionRepository.getMoods()
}