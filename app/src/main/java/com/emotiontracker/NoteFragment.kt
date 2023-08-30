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

private const val EMOTION = "emotion"

class NoteFragment: Fragment() {

    interface Callbacks{
        fun onSaveNoteSelected(){
        }
    }

    private var callbacks: Callbacks? = null

    private var _binding: NoteFragmentBinding? = null
    private val binding: NoteFragmentBinding
        get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()

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

        val emotion: String? = requireArguments().getString(EMOTION)
        //val emotionClassName = emotionClass!!::class.simpleName
        val emotionClass = emotion?.let { Emotion.getFromSimpleName(it) }


        binding.emotionName.text = emotionClass?.name?.let { getString(it) }

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
                noteViewModel.note = sequence.toString()
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
            val mood = Mood(id = null, emotion, noteViewModel.note, noteViewModel.date)
            noteViewModel.addMood(mood)

            callbacks?.onSaveNoteSelected()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(emotion: String) =
            NoteFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(EMOTION, emotion)
                    }
                }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }

