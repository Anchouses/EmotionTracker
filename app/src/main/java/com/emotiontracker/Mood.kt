package com.emotiontracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Mood(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "emotionClassName")
    val simpleName: String?,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "date")
    val date: Date
) {

}