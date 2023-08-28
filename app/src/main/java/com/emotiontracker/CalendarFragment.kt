package com.emotiontracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emotiontracker.databinding.CalendarFragmentBinding
import java.util.Calendar

class CalendarFragment : Fragment() {

    private var _binding: CalendarFragmentBinding? = null
    private val binding: CalendarFragmentBinding
        get() = _binding!!

    private lateinit var moodRecyclerView: RecyclerView
    private var adapter = MoodAdapter(emptyList())
    private val emotionViewModel: EmotionViewModel by viewModels()
    private var item: Int  = 0
    private var selectDate: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalendarFragmentBinding.inflate(inflater, container, false)
        moodRecyclerView = binding.rwMood
        moodRecyclerView.layoutManager = LinearLayoutManager(context)
        moodRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        emotionViewModel.moodListLiveData.observe(viewLifecycleOwner) { moods ->
            moods?.let {
                updateUI(moods)
            }
        }

        val calendar = Calendar.getInstance()

        fun getDate(year: Int, month: Int, day: Int): Long{
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DATE, day)
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar.timeInMillis
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->

            selectDate = getDate(year, month, dayOfMonth)

            emotionViewModel.moodListLiveData.observe(   //используется для регистрации наблюдателя за экземпляром LiveData
                viewLifecycleOwner,       // определяет время жизни наблюдателя. Владелец жизненного цикла тут фрагмент, viewLifecycleOwner - его интерфейс
                Observer { moods: List<Mood> ->       // Observer -  объект, отвечающий за реакцию на новые данные LiveData - наблюдатель
                    moods.forEach {
                        if (it.date.time == selectDate) {
                            item = moods.indexOf(it)
                            moodRecyclerView.smoothScrollToPosition(item)
                        }
                    }
                }
            )
        }
    }

    private fun updateUI(moods: List<Mood>){
        adapter = MoodAdapter(moods)
        moodRecyclerView.adapter = adapter
    }

    inner class MoodViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val moodDate: TextView = itemView.findViewById(R.id.mood_date)
        private val moodNote: TextView = itemView.findViewById(R.id.mood_note)
        private val moodName: TextView = itemView.findViewById(R.id.mood_name)
        private val moodCard: CardView = itemView.findViewById(R.id.item_card)

        @SuppressLint("ResourceAsColor")
        fun bind(mood: Mood){
            moodDate.text = DateFormat.format("dd.MM.yy", mood.date).toString()
            moodName.text = emotionViewModel.emotions[mood.emotionId].name
            moodNote.text = mood.note
            moodCard.setCardBackgroundColor(resources.getColorStateList(emotionViewModel.emotions[mood.emotionId].color,
                context?.theme))
        }
    }

    inner class MoodAdapter(var moods: List<Mood>): RecyclerView.Adapter<MoodViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
            val view = layoutInflater.inflate(R.layout.item_mood, parent, false)
            return  MoodViewHolder(view)
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
