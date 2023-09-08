package com.emotiontracker.data.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "simpleName")
    val simpleName: String?,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "date")
    val date: Date
)