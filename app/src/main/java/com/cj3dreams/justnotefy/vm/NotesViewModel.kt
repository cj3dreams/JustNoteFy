package com.cj3dreams.justnotefy.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.justnotefy.model.NotesResponse
import com.cj3dreams.justnotefy.repo.RemoteNotesRepository
import com.cj3dreams.justnotefy.source.remote.Resource
import com.cj3dreams.justnotefy.source.remote.RestApiRequests
import kotlinx.coroutines.launch

class NotesViewModel(api: RestApiRequests): ViewModel() {
    private val repository = RemoteNotesRepository(api)

    private val _noteResponse: MutableLiveData<Resource<NotesResponse>> = MutableLiveData()
    val newsResponse: LiveData<Resource<NotesResponse>> get() = _noteResponse

    fun getAllNotes() = viewModelScope.launch {
        _noteResponse.value = repository.getAllNotes()
    }
}