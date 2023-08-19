package com.emotiontracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.emotiontracker.Emotion
import com.emotiontracker.Mood

@Database (entities = [Mood::class], version = 2)
@TypeConverters(EmotionTypeConverters::class)
abstract class EmotionDatabase: RoomDatabase() {

    abstract fun emotionDao(): EmotionDao

}