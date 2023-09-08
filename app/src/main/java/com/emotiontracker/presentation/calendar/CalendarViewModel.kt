package com.emotiontracker.presentation.calendar

import androidx.lifecycle.ViewModel
import com.emotiontracker.domain.EmotionInteractor
import com.emotiontracker.presentation.navigation.FragmentNavigator
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

class CalendarViewModel(private var emotionInteractor: EmotionInteractor) : ViewModel() {

    private var fragmentNavigator: FragmentNavigator? = null
    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.fragmentNavigator = fragmentNavigator
    }

    val moodModelLiveDataList = emotionInteractor.getMoods()
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

        val listMoods = moodModelLiveDataList.value
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

    fun onButtonBack(){
        fragmentNavigator?.showChoiceFragment()
    }
}