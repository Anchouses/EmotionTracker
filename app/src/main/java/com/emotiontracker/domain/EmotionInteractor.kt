package com.emotiontracker.domain

import androidx.lifecycle.LiveData
import com.emotiontracker.data.datamodel.Mood
import com.emotiontracker.domain.repository.EmotionRepository
import com.emotiontracker.domain.repository.RepositoryInterface

class EmotionInteractor: RepositoryInterface {

    private val emotionRepository = EmotionRepository.get()
    private val emotionDao = emotionRepository.emotionDao
    private val executor = emotionRepository.executor

    var emotionModel = EmotionModel()

    override fun getMoods(): LiveData<List<Mood>> = emotionDao.getMoods()

    override fun getMood(id: Int): LiveData<Mood> = emotionDao.getMood(id)

    override fun addMood(mood: Mood){
        executor.execute{
            emotionDao.addMood(mood)
        }
    }

    override fun updateMood(mood: Mood){
        executor.execute{
            emotionDao.updateMood(mood)
        }
    }

    override fun saveEmotion() {
        val mood = Mood(id = null, emotionModel.className, emotionModel.note, emotionModel.date)
        addMood(mood)
    }

}
