package com.example.acculynx.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import androidx.room.Room
import com.example.acculynx.R
import com.example.acculynx.data.db.AppDatabase
import com.example.acculynx.ui.questionList.QuestionListFragment
import kotlinx.android.synthetic.main.activity_main.*

lateinit var fragmentTransaction: FragmentTransaction
lateinit var db: AppDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        this.supportActionBar!!.setHomeButtonEnabled(false)
//todo need to get back arrow working

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "UserDB")
            .fallbackToDestructiveMigration()
            .build()

        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(
            R.id.frag_container,
            QuestionListFragment(this)
        )
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * allows user to backup through the creation flow
     * it also notifies the user if they are about the exit the app
     * since we dont save our fragment histories
     */
    override fun onBackPressed() {
        supportFragmentManager.popBackStack()

    }
}