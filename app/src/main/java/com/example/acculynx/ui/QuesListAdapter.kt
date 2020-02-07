package com.example.acculynx.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.acculynx.R
import com.example.acculynx.data.models.Question
import kotlinx.android.synthetic.main.ques_list_item.view.*

class QuesListAdapter(private val activity: AppCompatActivity, private val quesList : List<Question>) : RecyclerView.Adapter<QuesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuesViewHolder {
        return QuesViewHolder(
            LayoutInflater.from(activity)
                .inflate(
                    R.layout.ques_list_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return quesList.size
    }

    override fun onBindViewHolder(holder: QuesViewHolder, position: Int) {
        val question = quesList[position]
        holder.title.text = HtmlCompat.fromHtml(question.title, 0)
//        holder.question.text = question.body
    }

}

class QuesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = (itemView.questionTitle)
        val question = itemView.questionText

}
