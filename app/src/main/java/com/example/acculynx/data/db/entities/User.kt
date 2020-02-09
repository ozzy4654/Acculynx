package com.example.acculynx.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    //todo need to setup auto increment and also handle initial install user creation
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "score") val score: Int?
)