package com.example.acculynx.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.acculynx.data.db.entities.RoomAnswers

@Dao
interface AnswersDao {

    @Query("SELECT * FROM RoomAnswers")
    fun getRecentGuessList() : List<RoomAnswers>

    @Insert
    fun saveAnswer(vararg ans: RoomAnswers)

    @Delete
    fun delete(ans : RoomAnswers)
}