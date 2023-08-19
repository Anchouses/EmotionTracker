package com.emotiontracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emotiontracker.databinding.CalendarFragmentBinding



class CalendarFragment : Fragment() {

    private var _binding: CalendarFragmentBinding? = null
    private val binding: CalendarFragmentBinding
        get() = _binding!!

    var year: Int = 0
    var month:Int = 0
    var day: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalendarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.calendarView.setOnDateChangeListener{ calendarView, y, m, d ->
            year = y
            month = m
            day = d
        }
    }

    companion object {
        fun newInstance() = CalendarFragment()
    }
}