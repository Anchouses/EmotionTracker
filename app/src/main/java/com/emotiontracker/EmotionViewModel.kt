package com.emotiontracker

import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class EmotionViewModel : ViewModel()  {

    var date: Date = Calendar.getInstance().apply {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time

    var note: String? = ""

    private val emotionRepository = EmotionRepository.get()

    val moodListLiveData = emotionRepository.getMoods()

    fun addMood(mood: Mood) {
        emotionRepository.addMood(mood)
    }

    val emotions = listOf(
        Emotion(0,"Злость", R.string.angry_description, R.color.angry),
        Emotion(1, "Досада", R.string.angry_light_description, R.color.angry),
        Emotion(2, "Гнев",R.string.angry_hard_description, R.color.angry),
        Emotion(3,"Страх", R.string.fear_description, R.color.fear),
        Emotion(4,"Тревога",R.string.fear_light_description, R.color.fear),
        Emotion(5,"Ужас",R.string.fear_hard_description, R.color.fear),
        Emotion(6,"Удивление", R.string.surprise_description, R.color.surprise),
        Emotion(7,"Отвлечение",R.string.surprise_light_description, R.color.surprise),
        Emotion(8,"Изумление",R.string.surprise_hard_description, R.color.surprise),
        Emotion(9,"Грусть", R.string.sad_description, R.color.sad),
        Emotion(10,"Печаль",R.string.sad_light_description, R.color.sad),
        Emotion(11,"Горе",R.string.sad_hard_description, R.color.sad),
        Emotion(12,"Неприязнь", R.string.dislike_description, R.color.dislike),
        Emotion(13,"Неодобрение",R.string.dislike_light_description, R.color.dislike),
        Emotion(14,"Отвращение",R.string.dislike_hard_description, R.color.dislike),
        Emotion(15,"Предвосхищение", R.string.interest_description, R.color.interest),
        Emotion(16,"Интерес",R.string.interest_light_description, R.color.interest),
        Emotion(17,"Бдительность",R.string.interest_hard_description, R.color.interest),
        Emotion(18,"Радость", R.string.joy_description, R.color.joy),
        Emotion(19,"Безмятежность", R.string.joy_light_description, R.color.joy),
        Emotion(20,"Восторг",R.string.joy_hard_description,R.color.joy),
        Emotion(21,"Доверие", R.string.trust_description, R.color.trust),
        Emotion(22,"Одобрение", R.string.trust_light_description, R.color.trust),
        Emotion(23,"Восхищение", R.string.trust_hard_description, R.color.trust))

}