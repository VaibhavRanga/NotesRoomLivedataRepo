package com.example.notesroomlivedatarepo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.notesroomlivedatarepo.models.NoteModel

@Dao
interface NoteDao {

    @Upsert
    fun upsertNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel)

    @Query("select * from note_table")
    fun getAllNotes() : LiveData<List<NoteModel>>
}
