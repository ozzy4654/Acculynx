package com.example.acculynx.data.models

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("accepted_answer_id")
    val acceptedAnswerId: Int,
    @SerializedName("answer_count")
    val answerCount: Int,
    @SerializedName("answers")
    val answers: List<Answer>,
    @SerializedName("body")
    val body: String,
    @SerializedName("is_answered")
    val isAnswered: Boolean,
    @SerializedName("link")
    val link: String,
    @SerializedName("question_id")
    val questionId: Int,
    @SerializedName("title")
    val title: String
)