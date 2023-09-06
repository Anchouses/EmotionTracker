package com.emotiontracker.domain

import androidx.lifecycle.LiveData
import com.emotiontracker.data.datamodel.Mood
import java.util.Date

interface RepositoryInterface {

    fun saveEmotion(className: String?, note: String?, date: Date)

    fun getMoods(): LiveData<List<Mood>>

    fun getMood(id: Int): LiveData<Mood>

    fun addMood(mood: Mood)

    fun updateMood(mood: Mood)
}