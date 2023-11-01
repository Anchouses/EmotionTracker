package com.emotiontracker.data.database

import androidx.room.TypeConverter
import java.util.Date

class EmotionTypeConverters {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?):  Date? {
        return millisSinceEpoch?.let { Date(it) }
    }
}