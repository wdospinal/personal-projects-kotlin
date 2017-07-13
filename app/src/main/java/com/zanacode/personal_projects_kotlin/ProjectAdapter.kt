package com.zanacode.personal_projects_kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zanacode.personal_projects_kotlin.model.Project

/**
 * Created by dospinal on 13/07/2017.
 */

class ProjectAdapter// Provide a suitable constructor (depends on the kind of dataset)
(private val mDataset: Array<Project>) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case

        var mName: TextView
        var mUrl: TextView
        var mContent: TextView
        var mLikes: TextView

        init {
            mName = v.findViewById(R.id.name) as TextView
            mUrl = v.findViewById(R.id.url) as TextView
            mContent = v.findViewById(R.id.url) as TextView
            mLikes = v.findViewById(R.id.likes) as TextView
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectAdapter.ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_project, parent, false)
        val vh = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mName.text = mDataset[position].name
        holder.mUrl.text = mDataset[position].url
        holder.mContent.text = mDataset[position].content
        holder.mLikes.text = mDataset[position].likes.toString()

    }

    override fun getItemCount(): Int {
        return mDataset.size

    }
}