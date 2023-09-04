package com.emotiontracker.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emotiontracker.data.datamodel.Mood

@Dao
interface EmotionDao {

    @Query("SELECT * FROM Mood")
    fun getMoods(): LiveData<List<Mood>>

    @Query("SELECT * FROM Mood WHERE id=(:id)")  //
    fun getMood(id: Int): LiveData<Mood>

    @Insert  //сохранить заметку
    fun  addMood(mood: Mood)

    @Update  //обновить заметку
    fun updateMood(mood: Mood)


}