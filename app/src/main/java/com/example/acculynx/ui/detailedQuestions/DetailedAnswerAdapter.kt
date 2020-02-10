package com.example.acculynx.ui.detailedQuestions

import android.content.SharedPreferences
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.acculynx.R
import com.example.acculynx.data.db.entities.Answer
import com.example.acculynx.utils.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ans_list_item.view.*
import splitties.views.backgroundColor
import splitties.views.onClick

private lateinit var prefs: SharedPreferences

class DetailedAnswerAdapter(
    private val activity: AppCompatActivity,
    private val ansList: List<Answer>?
) : RecyclerView.Adapter<AnsViewHolder>() {

    private val detailedQuestionViewModel =
        ViewModelProvider(activity).get(DetailedQuestionViewModel::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnsViewHolder {
        //set up our prefs helper
        prefs = PreferenceHelper.customPrefs(
            activity.applicationContext,
            activity.applicationContext.packageName
        )

        detailedQuestionViewModel.score.value = (prefs.getInt(activity.getString(R.string.score_key), 0))
        // Create the observer which updates the UI.
        val scoreObserver = Observer<Int> { newScore ->
            // Update the UI, in this case, a TextView.
            activity.scoreValue.text = newScore.toString()
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        detailedQuestionViewModel.score.observe(activity, scoreObserver)


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
        return ansList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AnsViewHolder, position: Int) {
        val answer = ansList?.get(position)

        holder.title.text = HtmlCompat.fromHtml(answer?.body ?: "", 0)

        //set our onclick method to view detailed question
        //Only count score once, i.e. don't let user spam correct ans to boost score
        holder.title.onClick {
            if (holder.answerView.tag != activity.getString(R.string.tag_selected)) {
                val tempScore = detailedQuestionViewModel.score.value ?: 0
                if (!answer?.isAccepted!!) {
                    //highlight the view red and then back to normal color
                    //Minus 1 point
                    holder.answerView.backgroundColor = RED
                    holder.answerView.tag = activity.getString(R.string.tag_selected)
                    holder.title.setColorClickableText(WHITE)
                    detailedQuestionViewModel.score.value = (tempScore - 1)

                } else {
                    //highlight the view green and then back to normal color
                    //Add 1 point
                    holder.answerView.backgroundColor = activity.getColor(R.color.colorPrimary)
                    holder.answerView.tag = activity.getString(R.string.tag_selected)
                    detailedQuestionViewModel.score.value = (tempScore + 1)
                }

                prefs.edit().putInt(activity.getString(R.string.score_key), detailedQuestionViewModel.score.value ?: 0).apply()
            }
        }
    }
}

class AnsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.answerDescription
    val answerView = itemView.answerItem
}