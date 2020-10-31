package com.example.madlevel5task1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Note")
data class Note(
        val title: String,
        val lastUpdated: Date,
        val text: String,

        @PrimaryKey(autoGenerate = true)
        val id: Long
)