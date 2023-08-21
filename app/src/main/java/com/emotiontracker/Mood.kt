package com.emotiontracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Entity
data class Mood(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "emotionId")
    val emotionId: Int,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "date")
    val date: Date
)