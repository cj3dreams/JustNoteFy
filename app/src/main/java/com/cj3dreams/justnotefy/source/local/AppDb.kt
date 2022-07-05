package com.cj3dreams.justnotefy.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cj3dreams.justnotefy.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun noteDao(): NotesDao?

}