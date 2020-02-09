package com.example.acculynx.data.db.entities

import androidx.room.*
import com.example.acculynx.data.db.GsonTypeConverter
import com.google.gson.annotations.SerializedName

@Entity
data class RoomQuestions(
    @PrimaryKey
    @SerializedName("question_id")
    @ColumnInfo(name = "question_id")
    val questionId: Int,

    @SerializedName("accepted_answer_id")
    @ColumnInfo(name ="accepted_answer_id")
    val acceptedAnswerId: Int?,

    @SerializedName("answer_count")
    @ColumnInfo(name ="answer_count")
    val answerCount: Int?,

    @ColumnInfo(name ="answers")
    @TypeConverters(GsonTypeConverter::class)
    val answers : List<RoomAnswers>?,

    @ColumnInfo(name ="body")
    val body: String?,

    @SerializedName("is_answered")
    @ColumnInfo(name ="is_answered")
    val isAnswered: Boolean?,

    @ColumnInfo(name ="link")
    val link: String?,

    @ColumnInfo(name ="title")
    val title: String?
)