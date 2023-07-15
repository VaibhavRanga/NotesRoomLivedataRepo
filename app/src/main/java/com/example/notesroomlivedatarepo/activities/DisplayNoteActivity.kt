package com.example.notesroomlivedatarepo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesroomlivedatarepo.repository.NoteRepository
import com.example.notesroomlivedatarepo.database.NoteDao
import com.example.notesroomlivedatarepo.database.NoteDatabase
import com.example.notesroomlivedatarepo.models.NoteModel
import com.example.notesroomlivedatarepo.databinding.ActivityDisplayNoteBinding
import com.example.notesroomlivedatarepo.viewmodelfactories.DisplayNoteActivityNoteViewModelFactory
import com.example.notesroomlivedatarepo.viewmodels.DisplayNoteActivityNoteViewModel

class DisplayNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayNoteBinding
    private lateinit var noteDao: NoteDao
    private lateinit var displayNoteActivityNoteViewModel: DisplayNoteActivityNoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteDao = NoteDatabase.getDatabase(this@DisplayNoteActivity).noteDao()
        val noteRepository = NoteRepository(noteDao)

        displayNoteActivityNoteViewModel = ViewModelProvider(this@DisplayNoteActivity, DisplayNoteActivityNoteViewModelFactory(noteRepository)) [DisplayNoteActivityNoteViewModel::class.java]

        val toAdd = intent.getBooleanExtra("toAdd", false)
        val note = intent.getParcelableExtra<NoteModel>("note")
        var forUpdate = false

        if (toAdd) {
            binding.editTextNoteTitle.isEnabled = true
            binding.editTextNoteBody.isEnabled = true
        } else {
            binding.editTextNoteTitle.setText(note?.title)
            binding.editTextNoteBody.setText(note?.body)
        }

        binding.imageViewBackButton.setOnClickListener {
            val title = binding.editTextNoteTitle.text.toString()
            val body = binding.editTextNoteBody.text.toString()

            if (toAdd) {
                displayNoteActivityNoteViewModel.upsertNote(NoteModel(0, title, body))
            } else if (forUpdate) {
                displayNoteActivityNoteViewModel.upsertNote(NoteModel(note!!.id, title, body))
                forUpdate = false
            }
            onBackPressedDispatcher.onBackPressed()
        }

        binding.imageViewEditButton.setOnClickListener {
            binding.editTextNoteTitle.isEnabled = true
            binding.editTextNoteBody.isEnabled = true
            forUpdate = true
        }
    }
}
