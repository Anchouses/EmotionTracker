package com.emotiontracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mood(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "emotionId")
    val emotionId: Int,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "date")
    val date: String
)