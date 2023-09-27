package com.emotiontracker.domain

import kotlinx.coroutines.flow.Flow
import java.util.Date

class EmotionInteractor(private val repositoryInterface: RepositoryInterface) {

    fun getMoods(): Flow<List<MoodModel>> {
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
