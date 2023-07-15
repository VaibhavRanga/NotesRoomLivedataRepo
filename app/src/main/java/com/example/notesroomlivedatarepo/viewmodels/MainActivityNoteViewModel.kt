package com.example.notesroomlivedatarepo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notesroomlivedatarepo.repository.NoteRepository
import com.example.notesroomlivedatarepo.models.NoteModel

class MainActivityNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun deleteNote(note: NoteModel) {
        repository.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return repository.getAllNotes()
    }
}
