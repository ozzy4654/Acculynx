package com.example.acculynx.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.acculynx.data.network.models.QuestionWithAnswers
import com.example.acculynx.data.db.entities.Question

@Dao
interface QuestionsDao {

    @Transaction
    @Query("SELECT * FROM Question")
    fun getQuesWithAns(): LiveData<List<QuestionWithAnswers>>

    @Query("SELECT * FROM Question")
    fun getRecentGuessList() : LiveData<List<QuestionWithAnswers>>

    //if there is a question with same id replace it
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestion(question: Question)

    @Query("SELECT question_id FROM Question")
    fun getQuestionsIds() : LiveData<List<Int>>

    @Delete
    suspend fun delete(question : Question)
}