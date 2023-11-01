package com.emotiontracker.domain

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface RepositoryInterface {

    fun saveEmotion(className: String?, note: String?, date: Date)

    fun getMoods(): Flow<List<MoodModel>>

    fun addMood(moodModel: MoodModel)

    fun updateMood(moodModel: MoodModel)
}