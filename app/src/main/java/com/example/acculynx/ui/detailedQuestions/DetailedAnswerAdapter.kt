package com.example.acculynx.ui.detailedQuestions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.acculynx.R
import com.example.acculynx.data.db.AppDatabase
import com.example.acculynx.data.models.Answer
import kotlinx.android.synthetic.main.ans_list_item.view.*
import splitties.views.onClick

class DetailedAnswerAdapter(
    private val activity: AppCompatActivity,
    private val ansList: List<Answer>
) : RecyclerView.Adapter<AnsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnsViewHolder {
            return AnsViewHolder(
                LayoutInflater.from(activity)
                    .inflate(
                        R.layout.ans_list_item,
                        parent,
                        false
                    )
            )
        }

        override fun getItemCount(): Int {
            return ansList.size
        }

        override fun onBindViewHolder(holder: AnsViewHolder, position: Int) {
            val answer = ansList[position]

            holder.title.text = HtmlCompat.fromHtml(answer.body, 0)

            //set our onclick method to view detailed question
            holder.answerView.onClick{
                //for now toast it
                //todo need to create logic for selecting correct answer, dialogs, points etc....
                Toast.makeText(activity,"WOOT CLICK IS WROKING  accepted ${answer.isAccepted}", Toast.LENGTH_SHORT).show()
            }

            holder.title.onClick {
                //todo need to create logic for selecting correct answer, dialogs, points etc....
                Toast.makeText(activity,"WOOT CLICK IS WROKING  accepted ${answer.isAccepted}", Toast.LENGTH_SHORT).show()

            }
        }

    }

    class AnsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.answerDescription
        val answerView = itemView.answerItem
    }