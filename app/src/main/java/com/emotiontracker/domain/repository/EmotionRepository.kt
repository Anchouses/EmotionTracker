package com.emotiontracker.domain.repository

import android.content.Context
import androidx.room.Room
import com.emotiontracker.data.database.EmotionDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors


const val DATABASE_NAME = "Emotions"
class EmotionRepository private constructor(context: Context) {

    private val database: EmotionDatabase = Room.databaseBuilder(
        context.applicationContext,
        EmotionDatabase::class.java,
        DATABASE_NAME
    ).build()

    val emotionDao = database.emotionDao()

    val executor: Executor = Executors.newSingleThreadExecutor()


    companion object {
        private var INSTANCE: EmotionRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = EmotionRepository(context)
            }
        }

        fun get(): EmotionRepository {
            return INSTANCE ?:
            throw IllegalStateException("mklhhgg")
        }
    }
}