package com.cj3dreams.justnotefy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int? = 0,
    @ColumnInfo(name = "note")
    var note: String,
    @ColumnInfo(name = "colorOfNote")
    var colorOfNote: Int,
    @ColumnInfo(name = "createdAt")
    var createdAt: String,
    @ColumnInfo(name = "updatedAt")
    var updatedAt: String
    )
