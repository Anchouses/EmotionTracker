package com.emotiontracker


import androidx.lifecycle.MutableLiveData
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
    private val moodIdLiveData = MutableLiveData<Int>()

    val moodListLiveData = emotionRepository.getMoods()


    fun loadMood(id: Int) {
        moodIdLiveData.value = id
    }

    fun addMood(mood: Mood) {
        emotionRepository.addMood(mood)
    }

    val emotions = listOf(
        Emotion(0,"Злость", "Раздраженно-враждебное состояние", "#FFDC5959"),
        Emotion(1, "Досада", "Раздражение и неудовольствие из-за неудачи", "#FFDC5959"),
        Emotion(2, "Гнев","Бурная реакция негодования. Желание разрушения и проявления агрессии", "#FFDC5959"),
        Emotion(3,"Страх", "Состояние, вызванное реальной или предполагаемой опасностью", "#FF34B68D"),
        Emotion(4,"Тревога","Ощущение неопределенности, ожидание неприятных событий", "#FF34B68D"),
        Emotion(5,"Ужас","Сильный, всепоглощающий страх", "#FF34B68D"),
        Emotion(6,"Удивление", "Реакция на новое или неожиданное", "#FF00BCD4"),
        Emotion(7,"Отвлечение","Снижение концентрации и перенос внимания на что-то другое", "#FF00BCD4"),
        Emotion(8,"Изумление","Крайняя степень удивления, вызывающая полную концентрацию на ситуации", "#FF00BCD4"),
        Emotion(9,"Грусть", "Неудовлетворенность в каких-либо аспектах жизни", "#FF6C80F3"),
        Emotion(10,"Печаль","Спокойное переживание утраты, погруженность в себя", "#FF6C80F3"),
        Emotion(11,"Горе","Интенсивное переживание глубокой утраты", "#FF6C80F3"),
        Emotion(12,"Неприязнь", "Эмоциональное нежелание признавать или принять", "#FFDE79EF"),
        Emotion(13,"Неодобрение","Чувство неудовлетворенности чьими-то действиями или ситуацией", "#FFDE79EF"),
        Emotion(14,"Отвращение","Высшая степень неприязни, часто с физиологическими проявлениями", "#FFDE79EF"),
        Emotion(15,"Предвосхищение", "Ожидание или готовность к чему-либо", "#FFF88E64"),
        Emotion(16,"Интерес","Проявление внимания к чему-то значимому", "#FFF88E64"),
        Emotion(17,"Бдительность","Концентрация внимания, готовность реагировать на ситуацию", "#FFF88E64"),
        Emotion(18,"Радость", "Внутреннее чувство удовлетворения и удовольствия", "#FFFFEB3B"),
        Emotion(19,"Безмятежность", "Состояние умиротворения и гармонии", "#FFFFEB3B"),
        Emotion(20,"Восторг","Сильный всплеск радостных чувств","#FFFFEB3B"),
        Emotion(21,"Доверие", "Уверенность в безопасности и благоприятности ситуации", "#FFABF359"),
        Emotion(22,"Одобрение", "Признание ситуации хорошей и правильной", "#FFABF359"),
        Emotion(23,"Восхищение", "Позитивная реакция на талант, умения и красоту", "#FFABF359"))

}