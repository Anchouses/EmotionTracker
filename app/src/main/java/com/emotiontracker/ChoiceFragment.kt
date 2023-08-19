package com.emotiontracker

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.databinding.ChoiceFragmentBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        callbacks = activity as Callbacks

        binding.date.text  = DateFormat.format("Сегодня,  dd MMMM", emotionViewModel.date).toString()

        binding.angryButton.setOnClickListener{
            emotionChoice(1)
        }
        binding.fearButton.setOnClickListener{
            emotionChoice(4)
        }
        binding.surpriseButton.setOnClickListener{
            emotionChoice(7)
        }
        binding.sadButton.setOnClickListener{
            emotionChoice(10)
        }
        binding.dislikeButton.setOnClickListener{
            emotionChoice(13)
        }
        binding.interestButton.setOnClickListener{
            emotionChoice(16)
        }
        binding.joyButton.setOnClickListener{
            emotionChoice(19)
        }
        binding.trustButton.setOnClickListener{
            emotionChoice(22)
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
                binding.emotionDescription.text =emotionViewModel.emotions[i+1].description
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


