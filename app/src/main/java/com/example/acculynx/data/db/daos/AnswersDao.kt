package com.example.acculynx.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnswersDao {

    @Query("SELECT * FROM RoomAnswers")
    fun getRecentGuessList() : List<RoomAnswers>

    @Insert
    fun saveAnswer(vararg ans: RoomAnswers)

    @Delete
    fun delete(ans : RoomAnswers)
}