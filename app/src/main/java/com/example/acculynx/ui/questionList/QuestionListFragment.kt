package com.example.acculynx.ui.questionList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acculynx.R
import com.example.acculynx.data.db.AppDatabase
import com.example.acculynx.data.db.RoomQuestionViewModel
import com.example.acculynx.ui.MainActivity
import com.example.acculynx.ui.db
//import com.example.acculynx.ui.questionViewModel
import kotlinx.android.synthetic.main.fragment_ques_list.*


private lateinit var questionViewModel: QuestionViewModel
private lateinit var roomQuestionViewModel : RoomQuestionViewModel

class QuestionListFragment(val mainActivity: MainActivity) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ques_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Use view ModelFactory to initialize view model
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java
        )
        roomQuestionViewModel = ViewModelProvider(this).get(RoomQuestionViewModel::class.java)

        //get latest news from view model
        questionViewModel.getLatestQuestions()
        //observe viewModel live data
        questionViewModel.questionLiveData.observe(viewLifecycleOwner, Observer {
            //bind your ui here
            quesListRecycler.apply {
                layoutManager = LinearLayoutManager(activity)
                addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
                adapter = QuesListAdapter(mainActivity, it, roomQuestionViewModel)
            }

        })
    }
}