package com.emotiontracker.presentation.calendar

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emotiontracker.presentation.datasource.Emotion
import com.emotiontracker.presentation.navigation.FragmentNavigator
import com.emotiontracker.databinding.CalendarFragmentBinding
import com.emotiontracker.databinding.ItemMoodBinding
import com.emotiontracker.domain.MoodModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : Fragment() {

    private var _binding: CalendarFragmentBinding? = null
    private val binding: CalendarFragmentBinding
        get() = _binding!!

    private var _bindingItem: ItemMoodBinding? = null
    private val bindingItem: ItemMoodBinding
        get() = _bindingItem!!

    private var adapter = MoodAdapter(emptyList())

    private val calendarViewModel by viewModel <CalendarViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalendarFragmentBinding.inflate(inflater, container, false)
        binding.rwMood.layoutManager = LinearLayoutManager(context)
        binding.rwMood.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        calendarViewModel.initViewModel(FragmentNavigator(requireActivity() as AppCompatActivity))

        calendarViewModel.moodModelLiveDataList.observe(viewLifecycleOwner) { moods ->
            moods?.let {
                updateUI(moods)
            }
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendarViewModel.selectDate = calendarViewModel.getDate(year, month, dayOfMonth)
            calendarViewModel.getDateItem(calendarViewModel.selectDate)
            binding.rwMood.smoothScrollToPosition(calendarViewModel.item)
        }

        binding.buttonBack.setOnClickListener{
            calendarViewModel.onButtonBack()
        }
    }

    private fun updateUI(moods: List<MoodModel>){
        adapter = MoodAdapter(moods)
        binding.rwMood.adapter = adapter
    }

    inner class MoodAdapter(var moods: List<MoodModel>): RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

        inner class MoodViewHolder(view: View): RecyclerView.ViewHolder(view){
            private val moodDate = bindingItem.moodDate
            private val moodNote = bindingItem.moodNote
            private val moodName = bindingItem.moodName
            private val moodCard = bindingItem.itemCard

            fun bind(mood: MoodModel){
                val emotionClassName = mood.className
                val emotionClass = emotionClassName?.let { Emotion.getFromSimpleName(it) }
                moodName.text = emotionClass?.name?.let { getString(it) }
                moodDate.text = DateFormat.format("dd.MM.yy", mood.date).toString()
                moodNote.text = mood.note
                moodCard.setCardBackgroundColor(
                    emotionClass?.color?.let { resources.getColorStateList(it, null) }
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
            _bindingItem = ItemMoodBinding.inflate(layoutInflater, parent, false)
            return  MoodViewHolder(bindingItem.root)
        }

        override fun getItemCount(): Int {
            return moods.size
        }

        override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
            val mood = moods[position]
            holder.bind(mood)
        }
    }

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

