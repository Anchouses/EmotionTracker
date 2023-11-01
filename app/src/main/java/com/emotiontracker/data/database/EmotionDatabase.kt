package com.emotiontracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emotiontracker.data.datamodel.Mood

@Database (entities = [Mood::class], version = 2)
@TypeConverters(EmotionTypeConverters::class)
abstract class EmotionDatabase: RoomDatabase() {

    abstract fun emotionDao(): EmotionDao

}