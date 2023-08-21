package com.emotiontracker


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.UUID

class EmotionViewModel : ViewModel()  {

    var date: Date = Calendar.getInstance().apply {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time
    //Date = Date()//LocalDate = LocalDate.parse("dd MMMM yy")

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
        Emotion(0,"Злость", "Раздраженно-враждебное состояние", R.color.angry),
        Emotion(1, "Досада", "Раздражение и неудовольствие из-за неудачи", R.color.angry),
        Emotion(2, "Гнев","Бурная реакция негодования. Желание разрушения и проявления агрессии", R.color.angry),
        Emotion(3,"Страх", "Состояние, вызванное реальной или предполагаемой опасностью", R.color.fear),
        Emotion(4,"Тревога","Ощущение неопределенности, ожидание неприятных событий", R.color.fear),
        Emotion(5,"Ужас","Сильный, всепоглощающий страх", R.color.fear),
        Emotion(6,"Удивление", "Реакция на новое или неожиданное", R.color.surprise),
        Emotion(7,"Отвлечение","Снижение концентрации и перенос внимания на что-то другое", R.color.surprise),
        Emotion(8,"Изумление","Крайняя степень удивления, вызывающая полную концентрацию на ситуации", R.color.surprise),
        Emotion(9,"Грусть", "Неудовлетворенность в каких-либо аспектах жизни", R.color.sad),
        Emotion(10,"Печаль","Спокойное переживание утраты, погруженность в себя", R.color.sad),
        Emotion(11,"Горе","Интенсивное переживание глубокой утраты", R.color.sad),
        Emotion(12,"Неприязнь", "Эмоциональное нежелание признавать или принять", R.color.dislike),
        Emotion(13,"Неодобрение","Чувство неудовлетворенности чьими-то действиями или ситуацией", R.color.dislike),
        Emotion(14,"Отвращение","Высшая степень неприязни, часто с физиологическими проявлениями", R.color.dislike),
        Emotion(15,"Предвосхищение", "Ожидание или готовность к чему-либо", R.color.interest),
        Emotion(16,"Интерес","Проявление внимания к чему-то значимому", R.color.interest),
        Emotion(17,"Бдительность","Концентрация внимания, готовность реагировать на ситуацию", R.color.interest),
        Emotion(18,"Радость", "Внутреннее чувство удовлетворения и удовольствия", R.color.joy),
        Emotion(19,"Безмятежность", "Состояние умиротворения и гармонии", R.color.joy),
        Emotion(20,"Восторг","Сильный всплеск радостных чувств", R.color.joy),
        Emotion(21,"Доверие", "Уверенность в безопасности и благоприятности ситуации", R.color.trust),
        Emotion(22,"Одобрение", "Признание ситуации хорошей и правильной", R.color.trust),
        Emotion(23,"Восхищение", "Позитивная реакция на талант, умения и красоту", R.color.trust))

}