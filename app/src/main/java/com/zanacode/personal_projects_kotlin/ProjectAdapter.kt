package com.zanacode.personal_projects_kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zanacode.personal_projects_kotlin.model.Project
import kotlin.properties.Delegates

/**
 * Created by dospinal on 13/07/2017.
 */

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {


    //SE INICIALIZA LA LISTA VACIA
    var items: MutableList<Project> by
    Delegates.observable(mutableListOf(), { prop, old, new -> notifyDataSetChanged() })

    //LISTENER PARA EL LLAMADO DESDE LA ACTIVIDAD
    var onItemClickListener: ((Int) -> Unit)? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_project, parent, false)
        return ViewHolder(v, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(items[position])
    }

    fun getItemAt(mPos: Int) = items[mPos]

    //ELIMINAR
    fun removeItemByObject(mProject: Project){
        val mPosition = items.indexOfFirst { it.name == mProject.name }
        items.removeAt(mPosition)
        notifyItemRemoved(mPosition)
    }

    //AGREGAR
    fun add(mProject: Project){
        items.add(mProject)
        notifyDataSetChanged()
    }

    //ACTUALIZAR
    fun update(mProject: Project){
        val mPosition = items.indexOfFirst { it.name == mProject.name }
        items[mPosition] = mProject
        notifyItemChanged(mPosition)
    }

    class ViewHolder(v: View, var onItemClickListener: ((Int) -> Unit)?) :
            RecyclerView.ViewHolder(v) {
        var mName = v.findViewById(R.id.name) as TextView
        var mUrl = v.findViewById(R.id.url) as TextView
        var mContent = v.findViewById(R.id.content) as TextView
        var mLikes = v.findViewById(R.id.likes) as TextView

        fun bindHolder(mProject: Project){
            mName.text = mProject.name
            mUrl.text = mProject.url
            mContent.text =mProject.content
            mLikes.text = "(" + mProject.likes.toString() + ")"
        }
    }
}