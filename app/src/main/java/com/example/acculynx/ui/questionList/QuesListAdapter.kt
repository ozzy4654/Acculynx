package com.example.acculynx.ui.questionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.acculynx.R
import com.example.acculynx.data.network.models.QuestionWithAnswers
import com.example.acculynx.ui.detailedQuestions.DetailedQuestionFragment
import kotlinx.android.synthetic.main.ques_list_item.view.*
import splitties.views.onClick


class QuesListAdapter(
    private val activity: AppCompatActivity,
    private val questionViewModel: QuestionViewModel
) : RecyclerView.Adapter<QuesViewHolder>() {

    private var quesList : MutableList<QuestionWithAnswers>? = null

    internal fun setQuestions(questions: List<QuestionWithAnswers>?) {
        if (questions != null) {
            this.quesList = questions.toMutableList()
        }else
            this.quesList?.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuesViewHolder {
        return QuesViewHolder(LayoutInflater
            .from(activity)
            .inflate(R.layout.ques_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return quesList?.size?:0
    }

    override fun onBindViewHolder(holder: QuesViewHolder, position: Int) {
        val selectedQuestion = quesList?.get(position)?.question

        if (selectedQuestion != null) {
            holder.title.text = HtmlCompat.fromHtml(selectedQuestion.title.toString(), 0)
        }
        if (selectedQuestion != null) {
            holder.ansCount.text = selectedQuestion.answerCount.toString()
        }

        //set our onclick method to view detailed question
        holder.questionCard.onClick {

            selectedQuestion?.let { questionViewModel.insert(it) }

            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val frag = DetailedQuestionFragment()
            frag.setSelectedQuestion(quesList?.get(position)!!)
            fragmentTransaction.replace(R.id.frag_container,
                DetailedQuestionFragment()
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

        }
    }

}

class QuesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.questionTitle
    val ansCount = itemView.ansCount
    val questionCard = itemView.questionCard
}
