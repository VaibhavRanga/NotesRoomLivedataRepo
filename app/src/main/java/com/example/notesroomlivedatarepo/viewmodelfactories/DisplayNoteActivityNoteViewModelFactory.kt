package com.example.notesroomlivedatarepo.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesroomlivedatarepo.repository.NoteRepository
import com.example.notesroomlivedatarepo.viewmodels.DisplayNoteActivityNoteViewModel

class DisplayNoteActivityNoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DisplayNoteActivityNoteViewModel(repository) as T
    }
}
