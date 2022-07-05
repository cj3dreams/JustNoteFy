package com.cj3dreams.justnotefy.source.local

import androidx.room.*
import com.cj3dreams.justnotefy.model.NoteEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER by id DESC")
    suspend fun getAllNotes(): List<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteList(noteEntityList: List<NoteEntity>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity?)

    @Delete
    suspend fun deleteAllNote(noteEntity: NoteEntity?)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity?)
}