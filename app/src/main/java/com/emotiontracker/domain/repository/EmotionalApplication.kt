package com.emotiontracker.domain.repository

import android.app.Application

class EmotionalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        EmotionRepository.initialize(this)
    }
}