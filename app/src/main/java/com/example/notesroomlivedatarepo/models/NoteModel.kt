package com.example.notesroomlivedatarepo.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("note_table")
@Parcelize
data class NoteModel(

    @PrimaryKey(true)
    @ColumnInfo("note_id")
    val id: Int,

    @ColumnInfo("note_title")
    val title: String,

    @ColumnInfo("note_body")
    val body: String
) : Parcelable
