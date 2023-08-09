package com.emotiontracker

import android.os.Build
import android.os.Bundle
import android.provider.Settings.System.DATE_FORMAT
import android.provider.Settings.System.TIME_12_24
import android.text.format.DateFormat
import android.text.format.DateFormat.getLongDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.UUID


class ChoiceFragment: Fragment() {

    interface Callbacks{
        fun onEmotionSelected()
    }
    private var callbacks: Callbacks? = null
    private lateinit var angryButton: Button
    private lateinit var fearButton: Button
    private lateinit var surpriseButton: Button
    private lateinit var sadButton: Button
    private lateinit var dislikeButton: Button
    private lateinit var interestButton: Button
    private lateinit var joyButton: Button
    private lateinit var trustButton: Button

    private lateinit var dateText: TextView
    private lateinit var chosenEmotion: TextView
    private lateinit var emotionDescription: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var lightLevel: RadioButton
    private lateinit var middleLevel: RadioButton
    private lateinit var hardLevel: RadioButton
    private lateinit var choiceButton: Button

    private val emotionViewModel = EmotionViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.choice_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dateText = view.findViewById(R.id.date)
        dateText.text = DateFormat.format("Сегодня, dd MMMM", emotionViewModel.date).toString()

        angryButton = view.findViewById(R.id.angry)
        fearButton = view.findViewById(R.id.fear)
        surpriseButton = view.findViewById(R.id.surprise)
        sadButton = view.findViewById(R.id.sad)
        dislikeButton = view.findViewById(R.id.dislike)
        interestButton = view.findViewById(R.id.interest)
        joyButton = view.findViewById(R.id.joy)
        trustButton = view.findViewById(R.id.trust)

        chosenEmotion = view.findViewById(R.id.chosen_emotion)
        emotionDescription = view.findViewById(R.id.emotion_description)
        radioGroup = view.findViewById(R.id.radioGroup)
        lightLevel = view.findViewById(R.id.light)
        middleLevel = view.findViewById(R.id.middle)
        middleLevel.isChecked
        hardLevel = view.findViewById(R.id.hard)
        choiceButton = view.findViewById(R.id.choice_button)


        angryButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[0].name
            emotionDescription.text = emotionViewModel.emotions[0].description
            lightLevel.text = emotionViewModel.emotions[0].nameLight
            middleLevel.text = emotionViewModel.emotions[0].name
            hardLevel.text = emotionViewModel.emotions[0].nameHard
        }
        fearButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[1].name
            emotionDescription.text =emotionViewModel.emotions[1].description
            lightLevel.text = emotionViewModel.emotions[1].nameLight
            middleLevel.text = emotionViewModel.emotions[1].name
            hardLevel.text = emotionViewModel.emotions[1].nameHard
        }
        surpriseButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[2].name
            emotionDescription.text =emotionViewModel.emotions[2].description
            lightLevel.text = emotionViewModel.emotions[2].nameLight
            middleLevel.text = emotionViewModel.emotions[2].name
            hardLevel.text = emotionViewModel.emotions[2].nameHard
        }
        sadButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[3].name
            emotionDescription.text =emotionViewModel.emotions[3].description
            lightLevel.text = emotionViewModel.emotions[3].nameLight
            middleLevel.text = emotionViewModel.emotions[3].name
            hardLevel.text = emotionViewModel.emotions[3].nameHard
        }
        dislikeButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[4].name
            emotionDescription.text =emotionViewModel.emotions[4].description
            lightLevel.text = emotionViewModel.emotions[4].nameLight
            middleLevel.text = emotionViewModel.emotions[4].name
            hardLevel.text = emotionViewModel.emotions[4].nameHard
        }
        interestButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[5].name
            emotionDescription.text =emotionViewModel.emotions[5].description
            lightLevel.text = emotionViewModel.emotions[5].nameLight
            middleLevel.text = emotionViewModel.emotions[5].name
            hardLevel.text = emotionViewModel.emotions[5].nameHard
        }
        joyButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[6].name
            emotionDescription.text =emotionViewModel.emotions[6].description
            lightLevel.text = emotionViewModel.emotions[6].nameLight
            middleLevel.text = emotionViewModel.emotions[6].name
            hardLevel.text = emotionViewModel.emotions[6].nameHard
        }
        trustButton.setOnClickListener(){
            chosenEmotion.text = emotionViewModel.emotions[7].name
            emotionDescription.text =emotionViewModel.emotions[7].description
            lightLevel.text = emotionViewModel.emotions[7].nameLight
            middleLevel.text = emotionViewModel.emotions[7].name
            hardLevel.text = emotionViewModel.emotions[7].nameHard
        }

 //       radioGroup.setOnCheckedChangeListener() {
//            if (lightLevel.isChecked) {
//
//            }
 //       }


        choiceButton.setOnClickListener(){
            callbacks?.onEmotionSelected()
        }

    }



}