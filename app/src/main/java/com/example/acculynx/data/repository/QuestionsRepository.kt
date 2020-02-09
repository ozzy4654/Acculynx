package com.example.acculynx.data.repository

import com.example.acculynx.data.network.QuestionsInterface
import com.example.acculynx.data.models.Question
import com.example.acculynx.data.models.QuestionsResponse

class QuestionsRepository(
    private val apiInterface: QuestionsInterface
    ) : BaseRepository() {
    //get latest news using safe api call

    suspend fun getLatestQuestions() :  MutableList<Question>?{
        return safeApiCall(
            //await the result of deferred type
            call = {apiInterface.getQuestionsAsync().await()},
            error = "Error fetching questions"
            //convert to mutable list
        )?.questionList?.toMutableList()
    }


    suspend fun getLatestQuestionsResponse() :  QuestionsResponse?{
        return safeApiCall(
            //await the result of deferred type
            call = {apiInterface.getQuestionsAsync().await()},
            error = "Error fetching questions"
            //convert to mutable list
        )
    }

}