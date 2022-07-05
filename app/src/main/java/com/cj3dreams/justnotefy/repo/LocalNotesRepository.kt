package com.cj3dreams.justnotefy.repo

import com.cj3dreams.justnotefy.model.NoteEntity
import com.cj3dreams.justnotefy.source.local.NotesDao

class LocalNotesRepository(private val notesDao: NotesDao) {

    suspend fun getAllNews() = notesDao.getAllNotes()
    suspend fun updateNote(newsEntity: NoteEntity) = notesDao.updateNote(newsEntity)
    suspend fun deleteNote(newsEntity: NoteEntity) = notesDao.deleteAllNote(newsEntity)
    suspend fun insertNoteList(newsEntityList: List<NoteEntity>) = notesDao.insertNoteList(newsEntityList)
    suspend fun insertNote(newsEntity: NoteEntity?) = notesDao.insertNote(newsEntity)

}