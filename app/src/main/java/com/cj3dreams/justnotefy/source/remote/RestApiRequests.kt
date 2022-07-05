package com.cj3dreams.justnotefy.source.remote

import com.cj3dreams.justnotefy.model.NotesResponse
import retrofit2.http.GET
import retrofit2.http.POST

import retrofit2.http.Query

interface RestApiRequests {

    @GET("/classes/notes")
    suspend fun getAllNotes(): NotesResponse

    @POST("/classes/notes")
    suspend fun saveNote(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String)
}
