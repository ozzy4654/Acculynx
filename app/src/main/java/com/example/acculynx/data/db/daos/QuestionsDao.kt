package com.example.acculynx.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.acculynx.data.db.entities.RoomQuestions

@Dao
interface QuestionsDao {

    @Query("SELECT * FROM RoomQuestions")
    fun getRecentGuessList() : LiveData<List<RoomQuestions>>

    //if there is a question with same id replace it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestion(question: RoomQuestions)

    @Query("SELECT question_id FROM RoomQuestions")
    fun getQuestionsIds() : LiveData<List<Int>>

    @Delete
    suspend fun delete(question : RoomQuestions)
}