package com.example.mynotepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotepractice.DBHandler.DatabaseHandler
import com.example.mynotepractice.Model.Notes
import com.example.mynotepractice.RecyclerAdapter.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var fab:FloatingActionButton
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)

        setSupportActionBar(toolbar)

        ViewNotes()
    }

    private fun ViewNotes() {
        val dbHandler = DatabaseHandler(this)
        val notesList:ArrayList<Notes> = dbHandler.readNotes()

        recyclerView.adapter = NotesAdapter(this,notesList)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()

        fab.setOnClickListener {
            var intent:Intent = Intent(this,AddNoteActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}