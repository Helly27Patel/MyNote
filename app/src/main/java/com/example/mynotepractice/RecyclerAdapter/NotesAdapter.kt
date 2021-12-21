package com.example.mynotepractice.RecyclerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotepractice.DBHandler.DatabaseHandler
import com.example.mynotepractice.Model.Notes
import com.example.mynotepractice.R

class NotesAdapter(val context:Context,val notesList:ArrayList<Notes>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle:TextView = itemView.findViewById(R.id.txtNotesTitle)
        var txtNotes:TextView = itemView.findViewById(R.id.txtNotesText)
        var imgDelete:ImageView = itemView.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.notes_item_layout,parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text = notesList[position].title
        holder.txtNotes.text = notesList[position].note

        holder.imgDelete.setOnClickListener {
            var dbHandler = DatabaseHandler(context)

            var rows = dbHandler.deleteNotes(notesList[position].id!!)

            if (rows > 0){
                Toast.makeText(context,"Note Deleted!",Toast.LENGTH_LONG).show()
                notesList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,notesList.size)
            }
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}