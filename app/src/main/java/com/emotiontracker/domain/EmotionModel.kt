package com.emotiontracker.domain

import java.util.Calendar
import java.util.Date

class EmotionModel() { //className: String?, note: String?, date: Date
    var className: String? = null
    var note: String? = null
    var date: Date = Calendar.getInstance().time
}