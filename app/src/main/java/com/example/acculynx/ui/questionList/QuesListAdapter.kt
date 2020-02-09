package com.example.acculynx.ui.questionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.acculynx.R
import com.example.acculynx.data.db.AppDatabase
import com.example.acculynx.data.db.GsonTypeConverter
import com.example.acculynx.data.db.RoomQuestionViewModel
import com.example.acculynx.data.db.entities.RoomQuestions
import com.example.acculynx.data.models.Question
import com.example.acculynx.ui.detailedQuestions.DetailedQuestionFragment
import kotlinx.android.synthetic.main.ques_list_item.view.*
import splitties.views.onClick


class QuesListAdapter(
    private val activity: AppCompatActivity,
    private val quesList: List<Question>,
    val roomQuestionViewModel : RoomQuestionViewModel
) : RecyclerView.Adapter<QuesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuesViewHolder {


        return QuesViewHolder(LayoutInflater
            .from(activity)
            .inflate(R.layout.ques_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return quesList.size
    }

    override fun onBindViewHolder(holder: QuesViewHolder, position: Int) {
        val question = quesList[position]
        holder.title.text = HtmlCompat.fromHtml(question.title, 0)
        holder.ansCount.text = question.answerCount.toString()

        //set our onclick method to view detailed question
        holder.questionCard.onClick {


            // we want to save this question to our second list i.e.. our guess list
            // also need to make sure there is only one copy of the question
            val con = GsonTypeConverter()
            val dk = RoomQuestions(question.questionId, question.acceptedAnswerId, question.answerCount,
                con.someObjectListToString(question.answers), question.body, question.isAnswered,
                question.link, question.title
            )
            roomQuestionViewModel.insert(dk)
            println(" WE INSERTED THE QUESTION TO THE DBBBBB!!!!!!!!!!!!!!!!   ID NUMBER ${question.questionId}")


            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frag_container,
                DetailedQuestionFragment(question)
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
