package com.emotiontracker.domain

import androidx.lifecycle.LiveData
import java.util.Date

interface RepositoryInterface {

    fun saveEmotion(className: String?, note: String?, date: Date)

    fun getMoods(): LiveData<List<MoodModel>>

    fun getMood(id: Int): LiveData<MoodModel>

    fun addMood(moodModel: MoodModel)

    fun updateMood(moodModel: MoodModel)
}