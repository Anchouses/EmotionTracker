package com.emotiontracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotiontracker.databinding.NoteFragmentBinding
import java.util.UUID

private const val EMOTION_ID = "emotion_id"

class NoteFragment: Fragment() {

    interface Callbacks{
        fun onSaveNoteSelected(emotionId: Int){
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        callbacks = activity as Callbacks

        val emotionId = requireArguments().getInt(EMOTION_ID)

        binding.emotionName.text = emotionViewModel.emotions[emotionId].name
        binding.emotionName.setTextColor(emotionViewModel.emotions[emotionId].color)


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
            val mood = Mood(id = null, emotionId, emotionViewModel.note, emotionViewModel.date)
            emotionViewModel.addMood(mood)

            callbacks?.onSaveNoteSelected(emotionId)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(emotionId: Int) =
            NoteFragment().apply{
                arguments = Bundle().apply {
                    putInt(EMOTION_ID, emotionId)
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}