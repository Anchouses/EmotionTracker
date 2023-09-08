package com.emotiontracker.presentation.note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.emotiontracker.presentation.datasource.Emotion
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.databinding.NoteFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



const val EMOTION = "emotion"

class NoteFragment: Fragment() {

    private var _binding: NoteFragmentBinding? = null
    private val binding: NoteFragmentBinding
        get() = _binding!!

    private val noteViewModel by viewModel <NoteViewModel>()
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

        noteViewModel.emotionClassName = requireArguments().getString(EMOTION)

        noteViewModel.emotionClass = noteViewModel.emotionClassName?.let { Emotion.getFromSimpleName(it) }

         noteViewModel.emotionName = noteViewModel.emotionClass?.name?.let { getString(it) }

        binding.emotionName.text = noteViewModel.emotionName

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
        binding.editNote.addTextChangedListener(textWatcher)

        binding.buttonBack.setOnClickListener{
            noteViewModel.onButtonBack()
        }

        binding.saveNote.setOnClickListener{
            noteViewModel.saveEmotion(noteViewModel.emotionClassName, noteViewModel.note, noteViewModel.date)
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

