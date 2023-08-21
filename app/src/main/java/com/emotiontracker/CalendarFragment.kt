package com.emotiontracker

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
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.Month
import java.time.MonthDay
import java.time.Year
import java.util.Calendar
import java.util.Date
import java.util.Locale


private const val EMOTION_ID = "emotion_id"
class CalendarFragment : Fragment() {


    private var _binding: CalendarFragmentBinding? = null
    private val binding: CalendarFragmentBinding
        get() = _binding!!
    lateinit var moodRecyclerView: RecyclerView
    private var adapter = MoodAdapter(emptyList())
    private val emotionViewModel: EmotionViewModel by viewModels()

//
    private var year: Int = 0
    private var month:Int = 0
    private var day: Int = 0

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
        emotionViewModel.moodListLiveData.observe(   //используется для регистрации наблюдателя за экземпляром LiveData
            viewLifecycleOwner,       // определяет время жизни наблюдателя. Владелец жизненного цикла тут фрагмент, viewLifecycleOwner - его интерфейс
            Observer { moods ->       // Observer -  объект, отвечающий за реакцию на новые данные LiveData - наблюдатель
                moods?.let {          // когда список преступлений готов, наблюдатель посылает список в updateUI для adapter
                    updateUI(moods)
                }
            })

            //val emotionId = requireArguments().getInt(EMOTION_ID)
        var calendar = Calendar.getInstance()
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
        binding.calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->

            val millis: Long = getDate(year, month, dayOfMonth)

            binding.calendarName.text = millis.toString()

            var filterMoods: List<Mood>
            emotionViewModel.moodListLiveData.observe(   //используется для регистрации наблюдателя за экземпляром LiveData
                viewLifecycleOwner,       // определяет время жизни наблюдателя. Владелец жизненного цикла тут фрагмент, viewLifecycleOwner - его интерфейс
                Observer { moods ->       // Observer -  объект, отвечающий за реакцию на новые данные LiveData - наблюдатель
                    filterMoods = moods.filter { it.date.time == millis }
                    updateUI(filterMoods)
                }
            )
        }

//        binding.calendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
//            var curDate = dayOfMonth.toString()
//        })
    }


    private fun updateUI(moods: List<Mood>){
        adapter = MoodAdapter(moods)
        moodRecyclerView.adapter = adapter
    }

    //private fun updateDate(moods: List<Mood>)

    inner class MoodViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val moodDate: TextView = itemView.findViewById(R.id.mood_date)
        private val moodNote: TextView = itemView.findViewById(R.id.mood_note)
        private val moodName: TextView = itemView.findViewById(R.id.mood_name)
        private val moodCard: CardView = itemView.findViewById(R.id.item_card)


        fun bind(mood: Mood){
            moodDate.text = mood.date.time.toString() //DateFormat.format("dd MMMM yy", mood.date).toString()
            moodName.text = emotionViewModel.emotions[mood.emotionId].name
            moodNote.text = mood.note
            moodCard.setCardBackgroundColor(emotionViewModel.emotions[mood.emotionId].color) //= emotionViewModel.emotions[mood.emotionId].color
            //binding.calendarView.focusedMonthDateColor
        }

    }
    inner class MoodAdapter(var moods: List<Mood>): RecyclerView.Adapter<MoodViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
            val view = layoutInflater.inflate(R.layout.item_mood, parent, false) //layoutInflater.inflate(R.layout.item_mood, parent, false)
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
        fun newInstance(emotionId: Int) =
            CalendarFragment().apply {
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
