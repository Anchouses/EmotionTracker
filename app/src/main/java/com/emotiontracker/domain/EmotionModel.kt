package com.emotiontracker.domain

import java.util.Calendar
import java.util.Date

class EmotionModel{
    var className: String? = null
    var note: String? = null
    var date: Date = Calendar.getInstance().time
}