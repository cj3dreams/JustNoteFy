package com.cj3dreams.justnotefy.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.justnotefy.model.NotesResponse
import com.cj3dreams.justnotefy.model.PostResponse
import com.cj3dreams.justnotefy.repo.RemoteNotesRepository
import com.cj3dreams.justnotefy.source.remote.Resource
import com.cj3dreams.justnotefy.source.remote.RestApiRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class NotesViewModel(api: RestApiRequests): ViewModel() {
    private val repository = RemoteNotesRepository(api)

    private val _noteResponse: MutableLiveData<Resource<NotesResponse>> = MutableLiveData()
    val newsResponse: LiveData<Resource<NotesResponse>> get() = _noteResponse

    private val _postResponse: MutableLiveData<Resource<PostResponse>> = MutableLiveData()
    val postResponse: LiveData<Resource<PostResponse>> get() = _postResponse

    fun getAllNotes() = viewModelScope.launch {
        _noteResponse.value = repository.getAllNotes()
    }
    fun insertNote(note: String, autoid: String,
        colorOfNote: Int) = viewModelScope.launch {
        _postResponse.value = repository.insertNote(note, autoid, colorOfNote)
    }
}