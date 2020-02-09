package com.example.acculynx.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.acculynx.data.db.daos.AnswersDao
import com.example.acculynx.data.db.daos.QuestionsDao
import com.example.acculynx.data.db.daos.UserDao
import com.example.acculynx.data.db.entities.RoomAnswers
import com.example.acculynx.data.db.entities.RoomQuestions
import com.example.acculynx.data.db.entities.User

private const val DATABASE = "UserDB"

@Database(entities = arrayOf(User::class, RoomQuestions::class, RoomAnswers::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun questionDao(): QuestionsDao
    abstract fun answerDao(): AnswersDao


    //code below courtesy of https://github.com/googlesamples/android-sunflower; it is open
    //source just like this application.
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}