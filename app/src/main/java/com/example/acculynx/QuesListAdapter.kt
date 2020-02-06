package com.example.acculynx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class QuesListAdapter(private val activity: AppCompatActivity, private val quesList : ArrayList<StackOverFlowQuestions>) : RecyclerView.Adapter<QuesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuesViewHolder {
        return QuesViewHolder(LayoutInflater.from(activity)
            .inflate(R.layout.ques_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: QuesViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class QuesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


}
