package com.example.acculynx.ui.detailedQuestions

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acculynx.R
import com.example.acculynx.data.network.models.QuestionWithAnswers
import kotlinx.android.synthetic.main.detailed_question.*
import kotlinx.android.synthetic.main.ques_list_item.questionTitle


class DetailedQuestionFragment(
    private var selectedQuestion: QuestionWithAnswers
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detailed_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setHomeButtonEnabled(true)

        //set up the details
        questionTitle.text = HtmlCompat.fromHtml(selectedQuestion.question.title!!, 0)
        question.text = HtmlCompat.fromHtml(selectedQuestion.question.body!!, 0)

        //set up the recycler view for the answers
        //check to make sure answers are not null since room is being weird
        if (selectedQuestion.ansList.isNullOrEmpty()) {
            //we need to grab it from question model....dumb i know but its workaround for now.
            //todo come back and refactor this properly since using Room is still new for me
            selectedQuestion.ansList = selectedQuestion.question.answers?: emptyList()
        }

        ansRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            adapter = DetailedAnswerAdapter(
                activity as AppCompatActivity,
                selectedQuestion.ansList
            )
        }

    }


}