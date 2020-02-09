package com.example.acculynx.data.models


import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("answer_id")
    val answerId: Int,
    @SerializedName("is_accepted")
    val isAccepted: Boolean,
    @SerializedName("question_id")
    val questionId: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("body")
    val body: String
)