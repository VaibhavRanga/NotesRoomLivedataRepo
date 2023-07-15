package com.example.notesroomlivedatarepo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesroomlivedatarepo.repository.NoteRepository
import com.example.notesroomlivedatarepo.adapters.NoteRecyclerViewAdapter
import com.example.notesroomlivedatarepo.database.NoteDao
import com.example.notesroomlivedatarepo.database.NoteDatabase
import com.example.notesroomlivedatarepo.databinding.ActivityMainBinding
import com.example.notesroomlivedatarepo.models.NoteModel
import com.example.notesroomlivedatarepo.viewmodelfactories.MainActivityNoteViewModelFactory
import com.example.notesroomlivedatarepo.viewmodels.MainActivityNoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var noteAdapter: NoteRecyclerViewAdapter
    private lateinit var noteDao: NoteDao
    private lateinit var mainActivityNoteViewModel: MainActivityNoteViewModel
    private var isDoubleBackPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteDao = NoteDatabase.getDatabase(this@MainActivity).noteDao()
        val noteRepository = NoteRepository(noteDao)

        mainActivityNoteViewModel = ViewModelProvider(this@MainActivity, MainActivityNoteViewModelFactory(noteRepository)) [MainActivityNoteViewModel::class.java]

        noteAdapter = NoteRecyclerViewAdapter(this@MainActivity)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = noteAdapter

        binding.floatingActionButton.setOnClickListener {
            newNote()
        }

        mainActivityNoteViewModel.getAllNotes().observe(this@MainActivity) {
            noteAdapter.setList(it)
            noteAdapter.notifyDataSetChanged()
        }
    }

    private fun newNote() {
        val intent = Intent(this@MainActivity, DisplayNoteActivity::class.java)
        intent.putExtra("toAdd", true)
        startActivity(intent)
    }

    fun deleteNote(note: NoteModel) {
        val deleteNoteDialog = MaterialAlertDialogBuilder(this@MainActivity)
        deleteNoteDialog.setCancelable(false)
        deleteNoteDialog.setTitle("Delete ${note.title}")
        deleteNoteDialog.setMessage("Do you want to delete this note?")
        deleteNoteDialog.setPositiveButton("Yes") { dialog, which ->
            mainActivityNoteViewModel.deleteNote(note)
        }
        deleteNoteDialog.setNegativeButton("No") { dialog, which ->
        }
        deleteNoteDialog.show()
    }

    fun openNote(note: NoteModel) {
        val intent = Intent(this@MainActivity, DisplayNoteActivity::class.java)
        intent.putExtra("toAdd", false)
        intent.putExtra("note", note)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (isDoubleBackPressed) {
            onBackPressedDispatcher.onBackPressed()
            return
        }
        isDoubleBackPressed = true
        Toast.makeText(this@MainActivity, "Click back again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed( {
            isDoubleBackPressed = false
        }, 2000)
    }
}
