package com.example.notesroomlivedatarepo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesroomlivedatarepo.R
import com.example.notesroomlivedatarepo.activities.MainActivity
import com.example.notesroomlivedatarepo.models.NoteModel
import com.example.notesroomlivedatarepo.databinding.NoteListItemBinding
import kotlin.random.Random

class NoteRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {

    private val noteList = ArrayList<NoteModel>()

    fun setList(notes: List<NoteModel>) {
        noteList.clear()
        noteList.addAll(notes)
    }

    fun randomColor(): Int {
        val arrayColor = ArrayList<Int>().apply {
            add(R.color.color1)
            add(R.color.color2)
            add(R.color.color3)
            add(R.color.color4)
            add(R.color.color5)
            add(R.color.color6)
            add(R.color.color7)
            add(R.color.color8)
        }
        val randomIndex = Random.nextInt(arrayColor.size - 1)
        return arrayColor[randomIndex]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.bind(currentNote, context)
    }

    inner class NoteViewHolder(private val binding: NoteListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModel, context: Context) {
            binding.textViewNoteTitle.text = note.title
            binding.cardView.setCardBackgroundColor(context.resources.getColor(randomColor(), null))
            binding.linearLayout.setOnClickListener {
                (context as MainActivity).openNote(note)
            }
            binding.linearLayout.setOnLongClickListener {
                (context as MainActivity).deleteNote(note)
                true
            }
        }
    }
}
