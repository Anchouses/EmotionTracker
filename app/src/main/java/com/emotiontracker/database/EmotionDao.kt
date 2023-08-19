package com.emotiontracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emotiontracker.Emotion
import com.emotiontracker.Mood
import java.util.UUID

@Dao
interface EmotionDao {

//    @Query("SELECT * FROM Emotion")
//    fun getEmotions(): LiveData<List<Emotion>>

    @Query("SELECT * FROM Mood WHERE id=(:id)")  //
    fun getMood(id: Int): LiveData<Mood>

    @Insert  //сохранить заметку
    fun  addMood(mood: Mood)

    @Update  //обновить заметку
    fun updateMood(mood: Mood)


}