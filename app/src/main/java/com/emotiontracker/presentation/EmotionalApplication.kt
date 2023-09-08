package com.emotiontracker.presentation

import android.app.Application
import com.emotiontracker.data.repository.EmotionRepository
import com.emotiontracker.presentation.di.interactorModule
import com.emotiontracker.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EmotionalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        EmotionRepository.initialize(this)

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@EmotionalApplication)
            modules(listOf(interactorModule, presentationModule))
        }
    }
}