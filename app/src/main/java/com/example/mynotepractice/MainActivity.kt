package com.example.mynotepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var toolbar:androidx.appcompat.widget.Toolbar
    lateinit var fab:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        fab = findViewById(R.id.fab)

        setSupportActionBar(toolbar)
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