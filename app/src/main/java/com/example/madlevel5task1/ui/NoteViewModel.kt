package com.example.madlevel5task1.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.model.Note
import com.example.madlevel5task1.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val noteRepository: NoteRepository = NoteRepository(application.applicationContext)

    val note: LiveData<Note?> = noteRepository.getNotepad()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateNote(title: String, text: String) {
        val newNote = Note(
                title = title,
                lastUpdated = Date(),
                text = text,
                id = note.value?.id
        )

        if(isNoteValid(newNote)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNote(newNote)
                }

                success.value = true
            }
        }
    }

    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}