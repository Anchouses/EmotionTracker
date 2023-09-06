package com.emotiontracker.presentation.choice

import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.presentation.datasource.Emotion
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.R
import com.emotiontracker.databinding.ChoiceFragmentBinding



class ChoiceFragment: Fragment() {

    private var _binding: ChoiceFragmentBinding? = null
    private val binding: ChoiceFragmentBinding
        get() = _binding!!

    private val choiceViewModel: ChoiceViewModel by viewModels {ChoiceViewModel.Factory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChoiceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        choiceViewModel.initViewModel(FragmentNavigator(requireActivity() as AppCompatActivity))

        val now = choiceViewModel.date
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
            val emotionClass = choiceViewModel.currentEmotion
            if (emotionClass == null){
                Toast.makeText(context, R.string.choose_intensity, Toast.LENGTH_LONG).show()
            } else {
                val emotionClassName = emotionClass::class.simpleName
                if (emotionClassName != null) {
                    choiceViewModel.onForward(emotionClassName)
                }
            }
        }
    }

    private fun emotionChoice(emotion: Emotion){

        choiceViewModel.currentEmotion = emotion

        binding.chosenEmotion.text = getString(emotion.name)

        val (low, average, strong) = emotion.getIntensity()

        binding.emotionDescription.text = getString(emotion.description)
        binding.lightLevel.text = getString(low.name)
        binding.middleLevel.text = getString(average.name)
        binding.hardLevel.text = getString(strong.name)

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            if (binding.lightLevel.isChecked) {
                binding.chosenEmotion.text = getString(low.name)
                binding.emotionDescription.text = getString(low.description)
                choiceViewModel.currentEmotion = low
            }
            if (binding.middleLevel.isChecked) {
                binding.chosenEmotion.text = getString(average.name)
                binding.emotionDescription.text = getString(average.description)
                choiceViewModel.currentEmotion = average
            }
            if (binding.hardLevel.isChecked) {
                binding.chosenEmotion.text = getString(strong.name)
                binding.emotionDescription.text = getString(strong.description)
                choiceViewModel.currentEmotion = strong
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



