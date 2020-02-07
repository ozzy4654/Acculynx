package com.example.acculynx.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acculynx.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

private lateinit var questionViewModel: QuestionViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModelFactory = QuestionViewModelProvider.QuestionViewModelFactory()
        //Use view ModelFactory to initialize view model
        questionViewModel = ViewModelProvider.NewInstanceFactory().create(QuestionViewModel::class.java)
//        ViewModelProviders.of(this@MainActivity, viewModelFactory)
//                .get(NewsViewModel::class.java)

        questionViewModel
        //get latest news from view model
        questionViewModel.getLatestQuestions()
        //observe viewModel live data
        questionViewModel.questionLiveData.observe(this, Observer {
            //bind your ui here

            println("THE LIST BABYYYYYYYYY   ${it[0].link}")

            quesListRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = QuesListAdapter(
                    this@MainActivity,
                    it
                )
            }

        })


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
}
