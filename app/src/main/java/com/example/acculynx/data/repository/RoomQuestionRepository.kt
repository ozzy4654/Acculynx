package com.example.acculynx.data.repository

import androidx.lifecycle.LiveData
import com.example.acculynx.data.db.daos.QuestionsDao
import com.example.acculynx.data.db.entities.RoomQuestions

class RoomQuestionRepository(val questionsDao: QuestionsDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allIds: LiveData<List<Int>> = questionsDao.getQuestionsIds()

    suspend fun insert(question: RoomQuestions) {
        questionsDao.saveQuestion(question)
    }
}