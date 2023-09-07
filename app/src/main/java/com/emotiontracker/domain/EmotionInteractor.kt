package com.emotiontracker.domain

import androidx.lifecycle.LiveData
import com.emotiontracker.data.repository.EmotionRepository
import java.util.Date

class EmotionInteractor(
    emotionRepository: EmotionRepository): RepositoryInterface {

    private val repository = emotionRepository
    override fun getMoods(): LiveData<List<MoodModel>> {
        return repository.getMoods()
    }

    override fun getMood(id: Int): LiveData<MoodModel> {
       return repository.getMood(id)
    }

    override fun addMood(moodModel: MoodModel){
        repository.addMood(moodModel)
    }

    override fun updateMood(moodModel: MoodModel){
        repository.updateMood(moodModel)
    }

    override fun saveEmotion(className: String?, note: String?, date: Date) {
        val moodModel = MoodModel()
        moodModel.className = className
        moodModel.note = note
        moodModel.date = date
        addMood(moodModel)
    }
}
