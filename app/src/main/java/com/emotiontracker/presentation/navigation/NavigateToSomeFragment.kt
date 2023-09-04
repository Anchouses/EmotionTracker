package com.emotiontracker.presentation.navigation

interface NavigateToSomeFragment {

    fun showChoiceFragment()

    fun showNoteFragment(emotionClassName: String)

    fun showCalendarFragment()
}