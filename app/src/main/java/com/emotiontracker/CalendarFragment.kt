package com.emotiontracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emotiontracker.databinding.CalendarFragmentBinding


class CalendarFragment : Fragment() {

    private var _binding: CalendarFragmentBinding? = null
    private val binding: CalendarFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalendarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = CalendarFragment()
    }
}