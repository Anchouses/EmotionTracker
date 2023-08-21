package com.emotiontracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.format
import android.text.format.DateFormat.getBestDateTimePattern
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.databinding.ChoiceFragmentBinding
import java.text.DateFormat.getDateInstance
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.util.Calendar
import java.util.Calendar.DATE
import java.util.Calendar.HOUR
import java.util.Calendar.MILLISECOND
import java.util.Calendar.MINUTE
import java.util.Calendar.MONTH
import java.util.Calendar.SECOND
import java.util.Calendar.YEAR
import java.util.Locale

class ChoiceFragment: Fragment() {

    interface Callbacks{
        fun onEmotionSelected(emotionId: Int)
    }
    private var callbacks: Callbacks? = null


    private var _binding: ChoiceFragmentBinding? = null
    private val binding: ChoiceFragmentBinding
        get() = _binding!!

    private val emotionViewModel: EmotionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChoiceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        callbacks = activity as Callbacks

        val now = emotionViewModel.date//Calendar.getInstance().apply {
//            set(HOUR, 0)
//            set(MINUTE, 0)
//            set(SECOND, 0)
//            set(MILLISECOND, 0)
//        }

//        val to = StringBuilder().append(now.get(Calendar.DAY_OF_MONTH))
//            .append(".")
//            .append(now.get(MONTH) + 1)
//            .append(".")
//            .append(now.get(YEAR))

        //val today = now.time.toString()

        binding.date.text  = format("Сегодня, dd.MM.yy", now)

        binding.angryButton.setOnClickListener{
            emotionChoice(0)
        }
        binding.fearButton.setOnClickListener{
            emotionChoice(3)
        }
        binding.surpriseButton.setOnClickListener{
            emotionChoice(6)
        }
        binding.sadButton.setOnClickListener{
            emotionChoice(9)
        }
        binding.dislikeButton.setOnClickListener{
            emotionChoice(12)
        }
        binding.interestButton.setOnClickListener{
            emotionChoice(15)
        }
        binding.joyButton.setOnClickListener{
            emotionChoice(18)
        }
        binding.trustButton.setOnClickListener{
            emotionChoice(21)
        }
    }

    private fun emotionChoice(i: Int){
        binding.chosenEmotion.text = emotionViewModel.emotions[i].name
        binding.emotionDescription.text = emotionViewModel.emotions[i].description
        binding.lightLevel.text = emotionViewModel.emotions[i+1].name
        binding.middleLevel.text = emotionViewModel.emotions[i].name
        binding.hardLevel.text = emotionViewModel.emotions[i+2].name

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            if (binding.lightLevel.isChecked) {
                binding.chosenEmotion.text = emotionViewModel.emotions[i+1].name
                binding.emotionDescription.text = emotionViewModel.emotions[i+1].description
            }
            if (binding.middleLevel.isChecked) {
                binding.chosenEmotion.text = emotionViewModel.emotions[i].name
                binding.emotionDescription.text = emotionViewModel.emotions[i].description
            }
            if (binding.hardLevel.isChecked) {
                binding.chosenEmotion.text = emotionViewModel.emotions[i+2].name
                binding.emotionDescription.text = emotionViewModel.emotions[i+2].description
            }
        }

        binding.choiceButton.setOnClickListener{
            callbacks?.onEmotionSelected(i)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChoiceFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


