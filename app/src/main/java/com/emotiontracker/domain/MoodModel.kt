package com.emotiontracker.domain

import java.util.Date

data class MoodModel(
    val id: Int?,
    val className: String?,
    val note: String?,
    val date: Date
)