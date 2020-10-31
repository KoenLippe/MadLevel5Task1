package com.example.madlevel5task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task1.dao.NoteDao
import com.example.madlevel5task1.database.NoteRoomDatabase
import com.example.madlevel5task1.model.Note

class NoteRepository(context: Context) {

    private var noteDao: NoteDao

    init {
        val noteRoomDatabase = NoteRoomDatabase.getDatabase(context)
        noteDao = noteRoomDatabase!!.noteDao()
    }

    fun getNotepad(): LiveData<Note?> {
        return noteDao.getNotepad()
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}