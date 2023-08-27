package com.emotiontracker.database

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.UUID

class EmotionTypeConverters {

    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?):  Date? {
        return millisSinceEpoch?.let { Date(it) }
    }
}