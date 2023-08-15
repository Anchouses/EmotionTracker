package com.emotiontracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emotiontracker.databinding.ChoiceFragmentBinding
import com.emotiontracker.databinding.IntensiveFragmentBinding


class IntensiveFragment : Fragment() {

//    interface Callbacks{
//        fun onEmotionSelected()
//    }
//    private var callbacks: Callbacks? = null

    private var _binding: IntensiveFragmentBinding? = null
    private val binding get() = _binding!!

    private val emotionViewModel = EmotionViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = IntensiveFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment IntensiveFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            IntensiveFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}