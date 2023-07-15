package com.example.notesroomlivedatarepo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.notesroomlivedatarepo.repository.NoteRepository
import com.example.notesroomlivedatarepo.models.NoteModel

class DisplayNoteActivityNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun upsertNote(note: NoteModel) {
        repository.upsertNote(note)
    }
}
