package com.emotiontracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.emotiontracker.database.EmotionDatabase
import java.util.UUID
import java.util.concurrent.Executors


private const val DATABASE_NAME = "Emotions"
class EmotionRepository private constructor(context: Context) {

    private val database: EmotionDatabase = Room.databaseBuilder(
        context.applicationContext,
        EmotionDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val emotionDao = database.emotionDao()

    //private val numberOfThreads: Int = 4
    //newFixedThreadPool(numberOfThreads)
    private val executor = Executors.newSingleThreadExecutor()

    fun getMoods(): LiveData<List<Mood>> = emotionDao.getMoods()

    fun getMood(id: Int): LiveData<Mood> = emotionDao.getMood(id)

    fun addMood(mood: Mood){
        executor.execute{
            emotionDao.addMood(mood)
        }
    }

    fun updateMood(mood: Mood){
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

        fun get(): EmotionRepository{
            return INSTANCE ?:
            throw IllegalStateException("mklhhgg")
        }
    }
}