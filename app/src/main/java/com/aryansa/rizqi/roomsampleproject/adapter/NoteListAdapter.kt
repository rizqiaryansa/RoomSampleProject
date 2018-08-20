package com.aryansa.rizqi.roomsampleproject.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aryansa.rizqi.roomsampleproject.R
import com.aryansa.rizqi.roomsampleproject.model.Note
import kotlinx.android.synthetic.main.item_row_note.view.*

class NoteListAdapter(private val context: Context) :
        RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var mNotes: MutableList<Note>? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_note,
                parent, false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = if (mNotes != null) (MutableList) mNotes.size else 0

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val listNotes = mNotes?.get(position)
        holder.noteItemView.text = listNotes?.note
    }

    fun setNotes(listNotes: MutableList<Note>) {
        mNotes = listNotes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var noteItemView: TextView = view.findViewById(R.id.textView)
    }
}