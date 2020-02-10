package com.example.acculynx.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Question::class,
    parentColumns = arrayOf("question_id"),
    childColumns = arrayOf("question_id"),
    onDelete = CASCADE,
    onUpdate = CASCADE)
    ]
)
data class Answer (
    @PrimaryKey
    @SerializedName("answer_id")
    @ColumnInfo(name = "answer_id")
    val answerId: Int?,

    @SerializedName("is_accepted")
    @ColumnInfo(name = "is_accepted")
    val isAccepted: Boolean,

    @SerializedName("question_id")
    @ColumnInfo(name = "question_id")
    val questionId: Int,

    @ColumnInfo(name = "score")
    val score: Int,

    @ColumnInfo(name = "body")
    val body: String
)