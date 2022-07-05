package com.cj3dreams.justnotefy.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.justnotefy.model.NoteEntity
import com.cj3dreams.justnotefy.repo.LocalNotesRepository
import com.cj3dreams.justnotefy.source.local.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(notesDao: NotesDao): ViewModel() {

    private val repository = LocalNotesRepository(notesDao)
    val notesData: MutableLiveData<List<NoteEntity?>> = MutableLiveData()


    fun getAllNews() = viewModelScope.launch(Dispatchers.IO) {
        notesData.postValue(repository.getAllNews())
    }

    fun insertNote(noteEntity: NoteEntity?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(noteEntity)
    }
}