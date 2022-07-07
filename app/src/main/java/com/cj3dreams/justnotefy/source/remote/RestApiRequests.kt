package com.cj3dreams.justnotefy.source.remote

import androidx.room.Update
import com.cj3dreams.justnotefy.model.DeleteResponse
import com.cj3dreams.justnotefy.model.NotesResponse
import com.cj3dreams.justnotefy.model.PostResponse
import com.cj3dreams.justnotefy.model.PutResponse
import retrofit2.http.*

import java.sql.Date

interface RestApiRequests {

    @Headers(
        "X-Parse-Application-Id: ZHG1r0aOaeYrOcC5J8FhFAXix5R0aGQ2KDq9fTvb",
        "X-Parse-REST-API-Key: 3fTKahfcObJIHtVGeslry90jtGM7yqKMK50SXA2q",
        "Content-Type: application/json")
    @GET("/classes/notes")
    suspend fun getAllNotes(): NotesResponse


    @FormUrlEncoded
    @Headers(
        "X-Parse-Application-Id: ZHG1r0aOaeYrOcC5J8FhFAXix5R0aGQ2KDq9fTvb",
        "X-Parse-REST-API-Key: 3fTKahfcObJIHtVGeslry90jtGM7yqKMK50SXA2q",
        "Content-Type: application/x-www-form-urlencoded")
    @POST("/classes/notes")
    suspend fun saveNote(
        @Field("note") note: String,
        @Field("autoid") autoid: String,
        @Field("colorOfNote") colorOfNote: String
    ): PostResponse

    @FormUrlEncoded
    @Headers(
        "X-Parse-Application-Id: ZHG1r0aOaeYrOcC5J8FhFAXix5R0aGQ2KDq9fTvb",
        "X-Parse-REST-API-Key: 3fTKahfcObJIHtVGeslry90jtGM7yqKMK50SXA2q",
        "Content-Type: application/x-www-form-urlencoded")
    @PUT("/classes/notes/{objectId}")
    suspend fun updateNote(
        @Path("objectId") objectId: String,
        @Field("note") note: String,
        @Field("colorOfNote") colorOfNote: String
    ): PutResponse

    @Headers(
        "X-Parse-Application-Id: ZHG1r0aOaeYrOcC5J8FhFAXix5R0aGQ2KDq9fTvb",
        "X-Parse-REST-API-Key: 3fTKahfcObJIHtVGeslry90jtGM7yqKMK50SXA2q"
    )
    @DELETE("/classes/notes/{objectId}")
    suspend fun deleteNote(
        @Path("objectId") objectId: String
    ): DeleteResponse
}
