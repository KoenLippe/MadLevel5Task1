package com.example.madlevel5task1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Note")
data class Note(

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "lastUpdate")
        val lastUpdated: Date,

        @ColumnInfo(name = "text")
        val text: String,

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long? = null
)