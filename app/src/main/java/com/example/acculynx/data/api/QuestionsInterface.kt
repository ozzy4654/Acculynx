package com.example.acculynx.data.api

import com.example.acculynx.data.models.Question
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface QuestionService {

    @GET("search/advanced?order=desc&sort=activity&accepted=True&answers=2&site=stackoverflow&filter=!*1NwY1dlzcu4WfNNArzwSwSOCF.Enzq.S2K*2yVtT")
    fun getQuestions(): Deferred<Response<Question>>

}