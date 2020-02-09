package com.example.acculynx.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.acculynx.data.db.entities.Answer

@Dao
interface AnswersDao {

    @Query("SELECT * FROM Answer")
    fun getRecentGuessList() : List<Answer>

    @Insert
    fun saveAnswer(vararg ans: Answer)

    @Delete
    fun delete(ans : Answer)
}