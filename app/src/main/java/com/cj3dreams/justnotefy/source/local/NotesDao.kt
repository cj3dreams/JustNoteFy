package com.cj3dreams.justnotefy.source.local

import androidx.room.*
import com.cj3dreams.justnotefy.model.NoteEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER by objectId DESC")
    suspend fun getAllNotes(): List<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteList(noteEntityList: List<NoteEntity>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity?)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity?)
//    @Query("DELETE FROM notes WHERE objectId = :objectId")
//    suspend fun deleteNote(objectId: String?)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity?)
}