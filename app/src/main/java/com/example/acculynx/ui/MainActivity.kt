package com.example.acculynx.ui

import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.acculynx.R
import com.example.acculynx.data.db.AppDatabase
import com.example.acculynx.ui.detailedQuestions.DetailedQuestionViewModel
import com.example.acculynx.ui.questionList.QuestionListFragment
import com.example.acculynx.utils.createNetworkCallback
import com.example.acculynx.utils.PreferenceHelper
import com.example.acculynx.utils.networkRequest
import kotlinx.android.synthetic.main.activity_main.*

lateinit var fragmentTransaction: FragmentTransaction
lateinit var db: AppDatabase

private lateinit var prefs: SharedPreferences

var networkCallback : NetworkCallback? = null
var cm : ConnectivityManager? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        this.supportActionBar!!.setHomeButtonEnabled(false)
        prefs = PreferenceHelper.customPrefs(applicationContext, applicationContext.packageName)


        val detailedQuestionViewModel = ViewModelProvider(this)
            .get(DetailedQuestionViewModel::class.java)
        // Create the observer which updates the UI.
        val scoreObserver = Observer<Int> { newScore -> scoreValue.text = newScore.toString() }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        detailedQuestionViewModel.score.observe(this, scoreObserver)
        //set initial score on launch
        detailedQuestionViewModel.score.value = (prefs.getInt(getString(R.string.score_key), 0))

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "QuestionDB")
            .fallbackToDestructiveMigration()
            .build()

        //network checker from Android Developers site
        networkCallback = createNetworkCallback(this)
        cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm!!.registerNetworkCallback(networkRequest, networkCallback)

        val fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_container, QuestionListFragment(this))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    override fun onStop() {
        super.onStop()
        cm?.unregisterNetworkCallback(networkCallback)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
//        hide/disable the arrow after press. since we
        if (supportFragmentManager.backStackEntryCount > 1) {
            this.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            this.supportActionBar!!.setHomeButtonEnabled(false)
        }
        return true
    }

    /**
     * allows user to backup through the creation flow
     * it also notifies the user if they are about the exit the app
     * since we dont save our fragment histories
     */
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()

    }
}
