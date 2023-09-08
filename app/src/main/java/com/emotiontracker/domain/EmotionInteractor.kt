package com.emotiontracker.domain

import androidx.lifecycle.LiveData
import java.util.Date

class EmotionInteractor(
    private val repositoryInterface: RepositoryInterface) {

    fun getMoods(): LiveData<List<MoodModel>> {
        return repositoryInterface.getMoods()
    }

    private fun addMood(moodModel: MoodModel){
        repositoryInterface.addMood(moodModel)
    }

    fun saveEmotion(className: String?, note: String?, date: Date) {
        val moodModel = MoodModel(id = null, className, note, date)
        addMood(moodModel)
    }
}
