package com.emotiontracker

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.databinding.ChoiceFragmentBinding

class ChoiceFragment: Fragment() {

    interface Callbacks{
        fun onEmotionSelected(emotionClass: Emotion)
    }
    private var callbacks: Callbacks? = null

    private var _binding: ChoiceFragmentBinding? = null
    private val binding: ChoiceFragmentBinding
        get() = _binding!!

    override fun onAttach(context: Context) {   //функция жизненного цикла Fragment - зачем ее переопределять (для установки и отмены свойства callbacks)
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

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

        val now = emotionViewModel.date

        binding.date.text  = format("Сегодня, dd.MM.yy", now)

        binding.angryButton.setOnClickListener{
            emotionChoice(Emotion.Angry.Anger())
        }
        binding.fearButton.setOnClickListener{
            emotionChoice(Emotion.Fearing.Fear())
        }
        binding.surpriseButton.setOnClickListener{
            emotionChoice(Emotion.Surprising.Surprise())
        }
        binding.sadButton.setOnClickListener{
            emotionChoice(Emotion.Sadness.Sad())
        }
        binding.dislikeButton.setOnClickListener{
            emotionChoice(Emotion.Aversion.Dislike())
        }
        binding.interestButton.setOnClickListener{
            emotionChoice(Emotion.Interesting.Anticipation())
        }
        binding.joyButton.setOnClickListener{
            emotionChoice(Emotion.Glad.Joy())
        }
        binding.trustButton.setOnClickListener{
            emotionChoice(Emotion.Trusting.Trust())
        }

        binding.choiceButton.setOnClickListener{
            val emotionClass = emotionViewModel.currentEmotion
            if (emotionClass == null){
                Toast.makeText(context, R.string.choose_intensity, Toast.LENGTH_LONG).show()
            } else {
                callbacks?.onEmotionSelected(emotionClass)
            }
        }
    }

    private fun emotionChoice(emotion: Emotion){
        emotionViewModel.currentEmotion = emotion

        binding.chosenEmotion.text = emotion.name

        val (low, average, strong) = emotion.getIntensity()

        binding.emotionDescription.text = getString(emotion.description)
        binding.lightLevel.text = low.name
        binding.middleLevel.text = average.name
        binding.hardLevel.text = strong.name
        emotionViewModel.name = average.name

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            if (binding.lightLevel.isChecked) {
                binding.chosenEmotion.text = low.name
                binding.emotionDescription.text = getString(low.description)
                emotionViewModel.currentEmotion = low
                emotionViewModel.name = low.name
            }
            if (binding.middleLevel.isChecked) {
                binding.chosenEmotion.text = average.name
                binding.emotionDescription.text = getString(average.description)
                emotionViewModel.currentEmotion = average
                emotionViewModel.name = average.name
            }
            if (binding.hardLevel.isChecked) {
                binding.chosenEmotion.text = strong.name
                binding.emotionDescription.text = getString(strong.description)
                emotionViewModel.currentEmotion = strong
                emotionViewModel.name = strong.name
            }

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


