package com.emotiontracker.domain

import java.util.Calendar
import java.util.Date

data class MoodModel(
    var id: Int? = null,
    var className: String? = null,
    var note: String? = null,
    var date: Date = Calendar.getInstance().time
)