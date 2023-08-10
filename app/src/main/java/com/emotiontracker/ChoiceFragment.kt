package com.emotiontracker

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emotiontracker.databinding.ChoiceFragmentBinding
import java.util.logging.Level

class ChoiceFragment: Fragment() {

    interface Callbacks{
        fun onEmotionSelected()
    }
    private var callbacks: Callbacks? = null

    private var _binding: ChoiceFragmentBinding? = null
    private val binding get() = _binding!!

    private val emotionViewModel = EmotionViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ChoiceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.date.text  = DateFormat.format("Сегодня,  dd MMMM", emotionViewModel.date).toString()

        binding.angryButton.setOnClickListener{
            levelChoice(0)
        }
        binding.fearButton.setOnClickListener(){
            levelChoice(1)
        }
        binding.surpriseButton.setOnClickListener(){
            levelChoice(2)
        }
        binding.sadButton.setOnClickListener(){
            levelChoice(3)
        }
        binding.dislikeButton.setOnClickListener(){
            levelChoice(4)
        }
        binding.interestButton.setOnClickListener(){
            levelChoice(5)
        }
        binding.joyButton.setOnClickListener(){
            levelChoice(6)
        }
        binding.trustButton.setOnClickListener(){
            levelChoice(7)
        }

        binding.choiceButton.setOnClickListener(){
            callbacks?.onEmotionSelected()
        }

    }
    private fun levelChoice(i: Int){
        binding.chosenEmotion.text = emotionViewModel.emotions[i].name
        binding.emotionDescription.text = emotionViewModel.emotions[i].description
        binding.lightLevel.text = emotionViewModel.emotions[i].nameLight
        binding.middleLevel.text = emotionViewModel.emotions[i].name
        binding.hardLevel.text = emotionViewModel.emotions[i].nameHard

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            if (binding.lightLevel.isChecked) {
                binding.chosenEmotion.text = emotionViewModel.emotions[i].nameLight
                binding.emotionDescription.text =emotionViewModel.emotions[i].descriptionLight
            }
            if (binding.middleLevel.isChecked) {
                binding.chosenEmotion.text = emotionViewModel.emotions[i].name
                binding.emotionDescription.text =emotionViewModel.emotions[i].description
            }
            if (binding.hardLevel.isChecked) {
                binding.chosenEmotion.text = emotionViewModel.emotions[i].nameHard
                binding.emotionDescription.text =emotionViewModel.emotions[i].descriptionHard
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


