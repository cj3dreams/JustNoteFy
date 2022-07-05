package com.cj3dreams.justnotefy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "notes", indices = [Index(value = ["note", "createdAt"], unique = true)]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,
    @ColumnInfo(name = "note")
    var note: String,
    @ColumnInfo(name = "colorOfNote")
    var colorOfNote: Int,
    @ColumnInfo(name = "createdAt")
    var createdAt: String,
    @ColumnInfo(name = "updatedAt")
    var updatedAt: String
    )
