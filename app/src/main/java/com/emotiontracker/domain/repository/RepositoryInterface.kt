package com.emotiontracker.domain.repository

import androidx.lifecycle.LiveData
import com.emotiontracker.data.datamodel.Mood

interface RepositoryInterface {

    fun saveEmotion()

    fun getMoods(): LiveData<List<Mood>>

    fun getMood(id: Int): LiveData<Mood>

    fun addMood(mood: Mood)

    fun updateMood(mood: Mood)
}