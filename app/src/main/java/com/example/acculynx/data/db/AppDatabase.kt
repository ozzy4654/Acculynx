package com.example.acculynx.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.acculynx.data.db.daos.AnswersDao
import com.example.acculynx.data.db.daos.QuestionsDao
import com.example.acculynx.data.db.daos.UserDao
import com.example.acculynx.data.db.entities.Answer
import com.example.acculynx.data.db.entities.Question
import com.example.acculynx.data.db.entities.User
import kotlinx.coroutines.CoroutineScope

private const val DATABASE = "UserDB"

@Database(entities = [User::class, Question::class, Answer::class], version = 14, exportSchema =
false)
@TypeConverters(GsonTypeConverter::class)
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

        fun getInstance(
            context: Context,
            viewModelScope: CoroutineScope
        ): AppDatabase {
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