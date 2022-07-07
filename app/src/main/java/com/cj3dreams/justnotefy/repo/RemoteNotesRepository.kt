package com.cj3dreams.justnotefy.repo

import com.cj3dreams.justnotefy.source.remote.RestApiRequests

class RemoteNotesRepository(private val api: RestApiRequests): BaseRepository() {

    suspend fun getAllNotes() = safeApiCall { api.getAllNotes() }

    suspend fun insertNote(note: String, autoid: String,
        colorOfNote: Int) = safeApiCall {
        api.saveNote(note, autoid, colorOfNote.toString()) }

    suspend fun updateNote(objectId: String, note: String, colorOfNote: Int) =
        safeApiCall { api.updateNote(objectId, note, colorOfNote.toString())  }

    suspend fun deleteNote(objectId: String) = safeApiCall { api.deleteNote(objectId) }

}