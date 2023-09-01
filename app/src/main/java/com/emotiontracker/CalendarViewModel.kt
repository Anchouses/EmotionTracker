package com.emotiontracker


import androidx.lifecycle.ViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar


class CalendarViewModel: ViewModel() {

    private val emotionRepository = EmotionRepository.get()
    val moodListLiveData = emotionRepository.getMoods()
    private val calendar = Calendar.getInstance()
    var selectDate: Long = 0
    var item = 0

    fun getDate(year: Int, month: Int, day: Int): Long {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DATE, day)
        return calendar.timeInMillis
    }

    fun getDateItem(selectDate: Long): Int {

        val selectLocalDate: LocalDate = Instant
            .ofEpochMilli(selectDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val listMoods = moodListLiveData.value
        listMoods?.forEach {

            val saveDate: LocalDate = Instant
                .ofEpochMilli(it.date.time)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            if (saveDate == selectLocalDate) {
                item = listMoods.indexOf(it)
            }
        }
        return item
    }

    var navigator: NavigateToSomeFragment? = null

    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.navigator = fragmentNavigator
    }

    fun onButtonBack(){
        navigator?.showChoiceFragment()
    }
}