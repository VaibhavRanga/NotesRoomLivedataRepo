package com.example.notesroomlivedatarepo.repository

import androidx.lifecycle.LiveData
import com.example.notesroomlivedatarepo.database.NoteDao
import com.example.notesroomlivedatarepo.models.NoteModel

class NoteRepository(private val dao: NoteDao) {

    fun upsertNote(note: NoteModel) {
        dao.upsertNote((note))
    }

    fun deleteNote(note: NoteModel) {
        dao.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return dao.getAllNotes()
    }
}
