package com.example.acculynx.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomAnswers (
    @PrimaryKey
    @ColumnInfo(name = "answer_id")
    val answerId: Int?,
    @ColumnInfo(name = "is_accepted")
    val isAccepted: Boolean,
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    @ColumnInfo(name = "score")
    val score: Int,
    @ColumnInfo(name = "body")
    val body: String
)