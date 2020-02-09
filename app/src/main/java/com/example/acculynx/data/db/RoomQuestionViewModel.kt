package com.example.acculynx.data.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acculynx.data.db.entities.RoomQuestions
import com.example.acculynx.data.repository.RoomQuestionRepository
import kotlinx.coroutines.launch

class RoomQuestionViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: RoomQuestionRepository
    // LiveData gives us updated words when they change.
    val allIds: LiveData<List<Int>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val questionsDao = AppDatabase.getInstance(application).questionDao()
        repository = RoomQuestionRepository(questionsDao)
        allIds = repository.allIds
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(queston: RoomQuestions) = viewModelScope.launch {
        repository.questionsDao.saveQuestion(queston)
    }
}