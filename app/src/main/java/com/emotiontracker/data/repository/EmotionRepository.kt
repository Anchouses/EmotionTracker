package com.emotiontracker.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.Room
import com.emotiontracker.data.database.EmotionDatabase
import com.emotiontracker.data.datamodel.Mood
import com.emotiontracker.domain.MoodModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors


const val DATABASE_NAME = "Emotions"
class EmotionRepository private constructor(context: Context) {

    private val database: EmotionDatabase = Room.databaseBuilder(
        context.applicationContext,
        EmotionDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val emotionDao = database.emotionDao()

    private val executor: Executor = Executors.newSingleThreadExecutor()

    fun getMoods(): LiveData<List<MoodModel>> {
        val oldList: LiveData<List<Mood>> = emotionDao.getMoods()

        return oldList.map { list ->
            list.map {
                MoodModel(
                    id = it.id,
                    className = it.simpleName,
                    note = it.note,
                    date = it.date
                )
            }
        }
    }

    fun getMood(id: Int): LiveData<MoodModel> {
        val mood: LiveData<Mood> = emotionDao.getMood(id)

        return mood.map { MoodModel(
            id = it.id,
            className = it.simpleName,
            note = it.note,
            date = it.date
            )
        }
    }

    fun addMood(moodModel: MoodModel) {
        val mood = Mood(id = null, moodModel.className, moodModel.note, moodModel.date)
        executor.execute{
            emotionDao.addMood(mood)
        }
    }

    fun updateMood(moodModel: MoodModel){
        val mood = Mood(id = null, moodModel.className, moodModel.note, moodModel.date)
        executor.execute{
            emotionDao.updateMood(mood)
        }
    }


    companion object {
        private var INSTANCE: EmotionRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = EmotionRepository(context)
            }
        }

        fun get(): EmotionRepository {
            return INSTANCE ?:
            throw IllegalStateException("where your repository?")
        }
    }
}