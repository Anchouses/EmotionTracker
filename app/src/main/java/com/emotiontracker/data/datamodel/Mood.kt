package com.emotiontracker.data.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Mood(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "simpleName")
    var simpleName: String?,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "date")
    val date: Date
)