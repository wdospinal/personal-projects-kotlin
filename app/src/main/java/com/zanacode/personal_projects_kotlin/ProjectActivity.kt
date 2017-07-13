package com.zanacode.personal_projects_kotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.FirebaseDatabase
import com.zanacode.personal_projects_kotlin.model.Project
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.content_project.*

class ProjectActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        val mDatabase = FirebaseDatabase.getInstance().reference
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        recyclerProjects.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(this)
        recyclerProjects.layoutManager = mLayoutManager

        // specify an adapter (see also next example)
        val projects = Array<Project>(10){Project("Colombia","colombia.co",9,"Colombia pais de maravillas")}
        val mAdapter = ProjectAdapter(projects)
        recyclerProjects.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_project, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
