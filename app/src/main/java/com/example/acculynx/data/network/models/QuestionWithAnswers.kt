package com.example.acculynx.data.network.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.acculynx.data.db.entities.Answer
import com.example.acculynx.data.db.entities.Question

data class QuestionWithAnswers(
    @Embedded
    val question: Question,

    @Relation(parentColumn = "question_id", entityColumn = "question_id")
    val ansList: List<Answer>
)