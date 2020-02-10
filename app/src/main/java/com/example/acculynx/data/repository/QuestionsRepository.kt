package com.example.acculynx.data.repository

import androidx.lifecycle.LiveData
import com.example.acculynx.data.db.daos.QuestionsDao
import com.example.acculynx.data.db.entities.Question
import com.example.acculynx.data.network.models.QuestionWithAnswers
import com.example.acculynx.data.network.QuestionsInterface
import com.example.acculynx.data.network.models.QuestionsResponse
import java.lang.Exception

class QuestionsRepository(
    private val apiInterface: QuestionsInterface,
    val questionsDao: QuestionsDao
) : BaseRepository() {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allQuestion: LiveData<List<QuestionWithAnswers>> = questionsDao.getQuesWithAns()

    suspend fun insert(question: Question) {
        questionsDao.saveQuestion(question)
    }

    //get latest questions using safe api call
    suspend fun getLatestQuestions(): MutableList<QuestionWithAnswers>? {
        val quesAndAnslist = ArrayList<QuestionWithAnswers>()

        try {
            val list = safeApiCall(
                //await the result of deferred type
                call = { apiInterface.getQuestionsAsync().await() },
                error = "Error fetching questions"
                //convert to mutable list
            )?.questionList?.toMutableList()

            list?.forEach {
                val k = it.answers?.let { it1 ->
                    QuestionWithAnswers(it, it1)
                }

                if (k != null) {
                    quesAndAnslist.add(k)
                }
            }
            return quesAndAnslist
        } catch (e: Exception) {
            return quesAndAnslist
        }
    }

    suspend fun getLatestQuestionsResponse(): QuestionsResponse? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getQuestionsAsync().await() },
            error = "Error fetching questions"
            //convert to mutable list
        )
    }

}