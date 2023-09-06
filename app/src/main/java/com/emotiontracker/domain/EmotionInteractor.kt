package com.emotiontracker.domain

import androidx.lifecycle.LiveData
import com.emotiontracker.data.datamodel.Mood
import com.emotiontracker.data.repository.EmotionRepository
import java.util.Date

class EmotionInteractor(
    emotionRepository: EmotionRepository,
    private val emotionModel: EmotionModel): RepositoryInterface {

    private val repository = emotionRepository
    override fun getMoods(): LiveData<List<Mood>> {
        return repository.getMoods()
    }

    override fun getMood(id: Int): LiveData<Mood> {
       return repository.getMood(id)
    }

    override fun addMood(mood: Mood){
        repository.addMood(mood)
    }

    override fun updateMood(mood: Mood){
        repository.updateMood(mood)
    }

    override fun saveEmotion(className: String?, note: String?, date: Date) {
        emotionModel.className = className
        emotionModel.note = note
        emotionModel.date = date
        val mood = Mood(id = null, emotionModel.className, emotionModel.note, emotionModel.date)
        addMood(mood)
    }
}
