package com.zanacode.personal_projects_kotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.*
import com.zanacode.personal_projects_kotlin.model.Project
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.content_project.*




class ProjectActivity : AppCompatActivity() {

    val mAdapter = ProjectAdapter()
    lateinit var mUrl: String
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        mDatabase = FirebaseDatabase.getInstance().reference
        mUrl = "messages"
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        recyclerProjects.setHasFixedSize(true)

        // use a linear layout manager
        val mLayoutManager = LinearLayoutManager(this)
        recyclerProjects.layoutManager = mLayoutManager

        recyclerProjects.adapter = mAdapter
        fireBaseInit()
    }

    fun fireBaseInit() {
        mDatabase.child(mUrl).addChildEventListener(
                object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                        val mProject = dataSnapshot.getValue(Project::class.java)
                        mAdapter.removeItemByObject(mProject)
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                        val mProject = dataSnapshot.getValue(Project::class.java)
                        mAdapter.update(mProject)
                    }

                    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                        //throw UnsupportedOperationException()
                    }

                    override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {

                        val mProject = dataSnapshot.getValue(Project::class.java)
                        mAdapter.add(mProject)
                    }
                })

        //CLICK PARA ELIMINAR
        mAdapter.onItemClickListener = {
            deleteProject(it)
        }
    }

    //ELIMINAR
    fun deleteProject(mPosition: Int) {

        val mProject = mAdapter.getItemAt(mPosition)
        mDatabase.child(mUrl)
                .orderByChild("name")
                .equalTo(mProject.name)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            val firstChild = dataSnapshot.children.iterator().next()
                            firstChild.ref.removeValue()
                        }
                    }
                })
    }

    //AGREGAR
    fun addProject(mProject: Project) {
        mDatabase.child(mUrl)
                .push()
                .setValue(mProject)
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
