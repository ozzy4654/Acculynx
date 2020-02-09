package com.example.acculynx.data.db

import androidx.room.*
import com.example.acculynx.data.models.Question

@Dao
interface QuestionsDao {

    @Query("SELECT * FROM RoomQuestions")
   suspend fun getRecentGuessList() : List<RoomQuestions>

    //if there is a question with same id replace it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestion(question: RoomQuestions)

    @Query("SELECT question_id FROM RoomQuestions")
    suspend fun getQuestionsIds() : List<Int>

    @Delete
    suspend fun delete(question : RoomQuestions)
}