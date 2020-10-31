package com.example.madlevel5task1.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.madlevel5task1.model.Note
import com.example.madlevel5task1.repository.NoteRepository

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val noteRepository: NoteRepository = NoteRepository(application.applicationContext)

    val note: LiveData<Note?> = noteRepository.getNotepad()
}