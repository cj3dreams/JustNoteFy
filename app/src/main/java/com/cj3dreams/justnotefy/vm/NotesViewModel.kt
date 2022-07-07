package com.cj3dreams.justnotefy.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.justnotefy.model.DeleteResponse
import com.cj3dreams.justnotefy.model.NotesResponse
import com.cj3dreams.justnotefy.model.PostResponse
import com.cj3dreams.justnotefy.model.PutResponse
import com.cj3dreams.justnotefy.repo.RemoteNotesRepository
import com.cj3dreams.justnotefy.source.remote.Resource
import com.cj3dreams.justnotefy.source.remote.RestApiRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class NotesViewModel(api: RestApiRequests): ViewModel() {
    private val repository = RemoteNotesRepository(api)

    var noteResponse: MutableLiveData<Resource<NotesResponse>> = MutableLiveData()

    private val _postResponse: MutableLiveData<Resource<PostResponse>> = MutableLiveData()
    val postResponse: LiveData<Resource<PostResponse>> get() = _postResponse

    private val _putResponse: MutableLiveData<Resource<PutResponse>> = MutableLiveData()
    val putResponse: LiveData<Resource<PutResponse>> get() = _putResponse

    private val _deleteResponse: MutableLiveData<Resource<DeleteResponse>> = MutableLiveData()
    val deleteResponse: LiveData<Resource<DeleteResponse>> get() = _deleteResponse


    fun getAllNotes() = viewModelScope.launch {
        noteResponse.value = repository.getAllNotes()
    }
    fun insertNote(note: String, autoid: String,
        colorOfNote: Int) = viewModelScope.launch {
        _postResponse.value = repository.insertNote(note, autoid, colorOfNote)
    }

    fun updateNote(objectId: String, note: String, colorOfNote: Int) = viewModelScope.launch {
        _putResponse.value = repository.updateNote(objectId, note, colorOfNote)
    }

    fun deleteNote(objectId: String) = viewModelScope.launch {
        _deleteResponse.value = repository.deleteNote(objectId)
    }
}