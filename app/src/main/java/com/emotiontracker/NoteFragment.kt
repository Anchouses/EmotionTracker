package com.emotiontracker

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.databinding.NoteFragmentBinding

private const val EMOTION_CLASS = "emotionClass"

class NoteFragment: Fragment() {

    interface Callbacks{
        fun onSaveNoteSelected(){
        }
    }

    private var callbacks: Callbacks? = null

    private var _binding: NoteFragmentBinding? = null
    private val binding: NoteFragmentBinding
        get() = _binding!!

    private val emotionViewModel: EmotionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NoteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        callbacks = activity as Callbacks

        val emotionClass: Emotion? = requireArguments().getSerializable(EMOTION_CLASS, Emotion::class.java)
        val emotionClassName = emotionClass!!::class.simpleName
        //val emotionClass = Emotion.getFromSimpleName(emotionClassName!!)

        binding.emotionName.text = emotionClass.name

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?,
                                           start: Int,
                                           count: Int,
                                           after: Int) {
                // это оставляем пустым специально
            }

            override fun onTextChanged(sequence: CharSequence?,
                                       start: Int,
                                       before: Int,
                                       count: Int) {
                emotionViewModel.note = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                //и это
            }
        }
        binding.editNote.addTextChangedListener(textWatcher)   //Добавляем TextWatcher в список методов, которые вызываются при изменении текста TextView.


        binding.buttonBack.setOnClickListener{
//            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
//                override fun handleOnBackPressed(){
//                }
//            })
        }

        binding.saveNote.setOnClickListener{
            val mood = Mood(id = null, emotionClassName, emotionViewModel.note, emotionViewModel.date)
            emotionViewModel.addMood(mood)

            callbacks?.onSaveNoteSelected()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(emotionClass: Emotion) =
            NoteFragment()
                .apply {
                    arguments = Bundle().apply {
                        putSerializable(EMOTION_CLASS, emotionClass)
                    }
                }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }

