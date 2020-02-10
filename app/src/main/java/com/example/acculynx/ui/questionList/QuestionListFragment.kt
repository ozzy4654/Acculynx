package com.example.acculynx.ui.questionList

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acculynx.R
import com.example.acculynx.data.network.models.QuestionWithAnswers
import com.example.acculynx.ui.MainActivity
import com.example.acculynx.utils.PreferenceHelper
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_ques_list.*


private lateinit var questionViewModel: QuestionViewModel
private lateinit var savedQuestionLiveData : LiveData<List<QuestionWithAnswers>>
private lateinit var apiQuestionLiveData : MutableLiveData<MutableList<QuestionWithAnswers>>
private lateinit var prefs : SharedPreferences

class QuestionListFragment(private val mainActivity: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ques_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set up our prefs helper
        prefs = PreferenceHelper.customPrefs(mainActivity.applicationContext, mainActivity.applicationContext.packageName)

        //Use view ModelFactory to initialize view model
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        //get latest questions from view model
        questionViewModel.getLatestQuestions()

        //live data vars
        apiQuestionLiveData = questionViewModel.questionLiveData
        savedQuestionLiveData = questionViewModel.allQuestion


        setRecycler()
        setTabListeners()

        // Add our observers on the LiveData returned by getQuestionsAndAnswers.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        savedQuestionLiveData.observe(viewLifecycleOwner, Observer { questions ->
            // Update the cached copy of the  in the adapter.
            if (prefs.getInt(mainActivity.getString(R.string.tab_pos_key), 0) == 1) {
                (quesListRecycler.adapter as QuesListAdapter).setQuestions(questions)
            }
        })

        apiQuestionLiveData.observe(viewLifecycleOwner, Observer {
            //bind your ui here
            if (prefs.getInt(mainActivity.getString(R.string.tab_pos_key), 0) == 0) {
                (quesListRecycler.adapter as QuesListAdapter).setQuestions(it)
            }

        })

    }

    private fun setRecycler() {
        quesListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = QuesListAdapter(mainActivity, questionViewModel)
        }

    }

    private fun setTabListeners() {
        // set our default tab to network questions
        tabLayout.getTabAt(prefs.getInt(getString(R.string.tab_pos_key), 0))?.let {
            tabLayout.selectTab(it)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.let { updateLists(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //do nothing
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { updateLists(it) }
            }

        })

    }

    private fun updateLists(tab: TabLayout.Tab) {
        when (tab?.position) {
            //network questions
            0 -> {
                updateTabPos(0)
                (quesListRecycler.adapter as QuesListAdapter).setQuestions(apiQuestionLiveData.value)
            }

            //saved questions
            1 -> {
                updateTabPos(1)
                (quesListRecycler.adapter as QuesListAdapter).setQuestions(savedQuestionLiveData.value)
            }
        }
    }

    private fun updateTabPos(position:Int) {
        prefs.edit()
            .putInt(mainActivity.getString(R.string.tab_pos_key), position)
            .apply()
    }
}