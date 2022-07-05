package com.cj3dreams.justnotefy.repo

import com.cj3dreams.justnotefy.source.remote.RestApiRequests

class RemoteNotesRepository(private val api: RestApiRequests): BaseRepository() {

    suspend fun getAllNotes() = safeApiCall { api.getAllNotes() }
//    suspend fun insertNote() = safeApiCall { api.saveNote() }

}