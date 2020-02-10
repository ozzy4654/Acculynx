package com.example.acculynx.ui.questionList

import android.app.Application
import androidx.lifecycle.*
import com.example.acculynx.data.db.AppDatabase
import com.example.acculynx.data.db.daos.QuestionsDao
import com.example.acculynx.data.db.entities.Question
import com.example.acculynx.data.network.models.QuestionWithAnswers
import com.example.acculynx.data.network.ApiServicesProvider
import com.example.acculynx.data.repository.QuestionsRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //initialize news repo
    private val questionsDao : QuestionsDao = AppDatabase.getInstance(application, viewModelScope).questionDao()

    private val questionsRepository : QuestionsRepository
    //live api data that will be populated as question updates
    val questionLiveData = MutableLiveData<MutableList<QuestionWithAnswers>>()

    // The ViewModel maintains a reference to the repository to get data.
    // LiveData gives us updated words when they change.
    val allQuestion: LiveData<List<QuestionWithAnswers>>

    init {
        // Gets reference to QuestionDao from AppDatabase to construct
        // the correct QuestionRepository.
        // structure here follows google code lab (Word list)
        questionsRepository = QuestionsRepository(ApiServicesProvider.questionService, questionsDao)
        allQuestion = questionsRepository.allQuestion
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(queston: Question) = viewModelScope.launch {
        questionsRepository.questionsDao.saveQuestion(queston)
    }



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