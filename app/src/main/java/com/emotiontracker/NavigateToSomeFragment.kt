package com.emotiontracker

interface NavigateToSomeFragment {

    fun showChoiceFragment()

    fun showNoteFragment(emotionClassName: String)

    fun showCalendarFragment()
}