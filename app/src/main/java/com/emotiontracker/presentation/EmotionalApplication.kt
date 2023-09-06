package com.emotiontracker.presentation

import android.app.Application
import com.emotiontracker.data.repository.EmotionRepository

class EmotionalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        EmotionRepository.initialize(this)
    }
}