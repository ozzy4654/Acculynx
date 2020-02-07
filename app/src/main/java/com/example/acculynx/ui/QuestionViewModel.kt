package com.example.acculynx.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acculynx.data.api.ApiServicesProvider
import com.example.acculynx.data.repository.QuestionsRepository
import com.example.acculynx.data.models.Question
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class QuestionViewModel : ViewModel() {

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //initialize news repo
    private val questionsRepository : QuestionsRepository =
        QuestionsRepository(
            ApiServicesProvider.questionService
        )
    //live data that will be populated as question updates
    val questionLiveData = MutableLiveData<MutableList<Question>>()

    fun getLatestQuestions() {
        ///launch the coroutine scope
        scope.launch {
            //get latest news from news repo
            val latestQuestions = questionsRepository.getLatestQuestions()

            //post the value inside live data
            questionLiveData.postValue(latestQuestions)

        }
    }
    fun cancelRequests() = coroutineContext.cancel()

}