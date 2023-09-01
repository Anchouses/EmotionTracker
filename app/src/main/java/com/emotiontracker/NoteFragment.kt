package com.emotiontracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.databinding.NoteFragmentBinding

private const val EMOTION = "emotion"

class NoteFragment: Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        noteViewModel.initViewModel(FragmentNavigator(requireActivity() as AppCompatActivity))

        val emotionClassName: String? = requireArguments().getString(EMOTION)

        val emotionClass = emotionClassName?.let { Emotion.getFromSimpleName(it) }

        binding.emotionName.text = emotionClass?.name?.let { getString(it) }

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?,
                                           start: Int,
                                           count: Int,
                                           after: Int) {
            }

            override fun onTextChanged(sequence: CharSequence?,
                                       start: Int,
                                       before: Int,
                                       count: Int) {
                noteViewModel.note = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
            }
        }
        binding.editNote.addTextChangedListener(textWatcher)   //Добавляем TextWatcher в список методов, которые вызываются при изменении текста TextView.

        binding.buttonBack.setOnClickListener{
            noteViewModel.onButtonBack()
        }

        binding.saveNote.setOnClickListener{
            val mood = Mood(id = null, emotionClassName, noteViewModel.note, noteViewModel.date)
            noteViewModel.addMood(mood)

            noteViewModel.onForward()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(emotionClassName: String) =
            NoteFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(EMOTION, emotionClassName)
                    }
                }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }

