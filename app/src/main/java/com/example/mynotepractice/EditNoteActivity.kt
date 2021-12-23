package com.example.mynotepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.mynotepractice.DBHandler.DatabaseHandler
import com.example.mynotepractice.Model.Notes

class EditNoteActivity : AppCompatActivity() {

    lateinit var toolbar:Toolbar
    var _id:Int = 0
    lateinit var edtUpdateTitle:EditText
    lateinit var edtUpdateNotes:EditText
    lateinit var btnUpdate:Button
    lateinit var notes:Notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        toolbar  =findViewById(R.id.toolbar)
        edtUpdateTitle = findViewById(R.id.edtUpdateTitle)
        edtUpdateNotes = findViewById(R.id.edtUpdateNote)
        btnUpdate = findViewById(R.id.btnUpdateNote)

        setSupportActionBar(toolbar)

        _id = intent.getIntExtra("_id",0)
    }

    override fun onStart() {
        super.onStart()

        var dbHanlder = DatabaseHandler(this)

        notes = dbHanlder.readSpecificNotes(_id)

        edtUpdateTitle.setText(notes.title)
        edtUpdateNotes.setText(notes.note)

        btnUpdate.setOnClickListener {
            updateNotes()
        }
    }

    fun updateNotes(){
        if (edtUpdateTitle.text.toString() == notes.title &&
                edtUpdateNotes.text.toString() == notes.note){
            finish()
        }else{
            var dbHandler = DatabaseHandler(this)

            var rows = dbHandler.updateNotes(Notes(_id,
                                        edtUpdateTitle.text.toString(),
                                        edtUpdateNotes.text.toString()))

            if (rows > 0){
                Toast.makeText(this,"Notes Updated!!",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}