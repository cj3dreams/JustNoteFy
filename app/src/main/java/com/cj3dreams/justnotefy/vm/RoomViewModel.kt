package com.cj3dreams.justnotefy.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj3dreams.justnotefy.model.NoteEntity
import com.cj3dreams.justnotefy.model.NotesResponse
import com.cj3dreams.justnotefy.model.Result
import com.cj3dreams.justnotefy.repo.LocalNotesRepository
import com.cj3dreams.justnotefy.source.local.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(notesDao: NotesDao): ViewModel() {

    private val repository = LocalNotesRepository(notesDao)
    val notesData: MutableLiveData<List<NoteEntity?>> = MutableLiveData()


    fun setNotesToDb(list: List<Result>) = viewModelScope.launch(Dispatchers.IO) {
        val noteList = mutableListOf<NoteEntity>().apply {
            list.forEach {
                this.add(NoteEntity(0,it.note, it.colorOfNote.toInt(), it.createdAt, it.updatedAt))
            }
        }
        repository.insertNoteList(noteList)
        notesData.postValue(repository.getAllNews())
    }

    fun getAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        notesData.postValue(repository.getAllNews())
    }

    fun insertNote(noteEntity: NoteEntity?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(noteEntity)
    }

    fun insertNoteList(noteEntityList: List<NoteEntity>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNoteList(noteEntityList)
    }

    fun updateNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(noteEntity)
    }

    fun deleteNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(noteEntity)
    }
}