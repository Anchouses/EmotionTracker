package com.emotiontracker

import android.app.Application

class EmotionalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        EmotionRepository.initialize(this)
    }
}