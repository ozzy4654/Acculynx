package com.example.acculynx.data.db.entities

import androidx.room.*
import com.example.acculynx.data.db.GsonTypeConverter

@Entity
data class RoomQuestions(
    @PrimaryKey
    @ColumnInfo(name = "question_id")
    val questionId: Int,
    @ColumnInfo(name ="accepted_answer_id")
    val acceptedAnswerId: Int?,
    @ColumnInfo(name ="answer_count")
    val answerCount: Int?,
    @ColumnInfo(name ="answers")
    @TypeConverters(GsonTypeConverter::class)
    val answers : String?,
    @ColumnInfo(name ="body")
    val body: String?,
    @ColumnInfo(name ="is_answered")
    val isAnswered: Boolean?,
    @ColumnInfo(name ="link")
    val link: String?,
    @ColumnInfo(name ="title")
    val title: String?
)