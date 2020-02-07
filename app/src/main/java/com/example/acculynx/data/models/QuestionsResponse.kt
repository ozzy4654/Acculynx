package com.example.acculynx.models


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