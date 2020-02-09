package com.example.acculynx.ui.questionList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuestionViewModelProvider {

    @Suppress("UNCHECKED_CAST")
    class QuestionViewModelFactory : ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QuestionViewModel() as T
        }
    }
}