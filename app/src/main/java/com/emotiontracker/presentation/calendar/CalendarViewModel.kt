package com.emotiontracker.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emotiontracker.domain.EmotionInteractor
import com.emotiontracker.domain.MoodModel
import com.emotiontracker.presentation.navigation.FragmentNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

class CalendarViewModel(emotionInteractor: EmotionInteractor) : ViewModel() {

    private var fragmentNavigator: FragmentNavigator? = null
    fun initViewModel(fragmentNavigator: FragmentNavigator){
        this.fragmentNavigator = fragmentNavigator
    }
    private var listDates = emptyList<MoodModel>()

    val moodModelFlowList: Flow<List<MoodModel>> = emotionInteractor.getMoods()

    private val calendar = Calendar.getInstance()
    var selectDate: Long = 0
    var item = 0

    fun getDate(year: Int, month: Int, day: Int): Long {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DATE, day)
        return calendar.timeInMillis
    }

    init {
        viewModelScope.launch {
            moodModelFlowList.collect(){
                listDates = it
            }
        }
    }

    fun getDateItem(selectDate: Long): Int {

        val selectLocalDate: LocalDate = Instant
            .ofEpochMilli(selectDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        listDates.forEach { mood ->
            val saveDate: LocalDate = Instant
                .ofEpochMilli(mood.date.time)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            if (saveDate == selectLocalDate) {
                item = listDates.indexOf(mood)
            }
        }

        return item
    }

    fun onButtonBack(){
        fragmentNavigator?.showChoiceFragment()
    }
}