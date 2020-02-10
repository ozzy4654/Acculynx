package com.example.acculynx.ui.detailedQuestions


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailedQuestionViewModel : ViewModel() {

    val score: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}