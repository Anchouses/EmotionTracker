package com.emotiontracker.presentation.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.emotiontracker.R
import com.emotiontracker.presentation.calendar.CalendarFragment
import com.emotiontracker.presentation.choice.ChoiceFragment
import com.emotiontracker.presentation.note.NoteFragment
import java.lang.ref.WeakReference

class FragmentNavigator (activity: AppCompatActivity): NavigateToSomeFragment {

    private val activityWeakReference: WeakReference<AppCompatActivity>

    init{
        activityWeakReference = WeakReference(activity)
    }

    private val fragmentManager: FragmentManager?
        get() {
        val activity: AppCompatActivity = activityWeakReference.get() ?: return null
        return activity.supportFragmentManager
    }

    private fun showFragment(fragment: Fragment, tag: String){
        val fragmentManager: FragmentManager = fragmentManager ?: return
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    private fun showCurrentFragment(fragment: Fragment){
        val tag = fragment.javaClass.name
        showFragment(fragment, tag)
    }

    override fun showChoiceFragment() {
        showCurrentFragment(ChoiceFragment.newInstance())
    }

    override fun showNoteFragment(emotionClassName: String) {
        showCurrentFragment(NoteFragment.newInstance(emotionClassName))
    }

    override fun showCalendarFragment() {
        showCurrentFragment(CalendarFragment.newInstance())
    }
}
