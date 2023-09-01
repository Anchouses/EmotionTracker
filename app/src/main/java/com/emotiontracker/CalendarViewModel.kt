package com.emotiontracker


import androidx.lifecycle.ViewModel
import java.util.Calendar


class CalendarViewModel: ViewModel() {

    private val emotionRepository = EmotionRepository.get()
    val moodListLiveData = emotionRepository.getMoods()
    private val calendar = Calendar.getInstance()
    var selectDate: Long = 0
    var item: Int = 0
    var listMoods: List<Mood> = emptyList()
    fun getDate(year: Int, month: Int, day: Int): Long {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DATE, day)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    fun getDateItem(selectDate: Long) {
        listMoods.forEach {
            if (it.date.time == selectDate) {
                item = listMoods.indexOf(it)
            }
        }
    }

    var navigator: NavigateToSomeFragment? = null

    fun navigateCalendarViewModel(fragmentNavigator: FragmentNavigator){
        this.navigator = fragmentNavigator
    }


}