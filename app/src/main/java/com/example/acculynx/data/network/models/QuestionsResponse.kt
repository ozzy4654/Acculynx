package com.example.acculynx.data.network.models


import com.example.acculynx.data.db.entities.Question
import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("items")
    val questionList: List<Question>,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)