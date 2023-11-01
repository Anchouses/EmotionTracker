package com.emotiontracker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.emotiontracker.data.datamodel.Mood
import kotlinx.coroutines.flow.Flow

@Dao
interface EmotionDao {

    @Query("SELECT * FROM Mood")
    fun getMoods(): Flow<List<Mood>>

    @Insert
    fun  addMood(mood: Mood)

    @Update
    fun updateMood(mood: Mood)


}