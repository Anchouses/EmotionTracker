package com.emotiontracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Emotion(
    var id: Int = 0,
    val name: String,
    val description: String,
    val color: String
)

