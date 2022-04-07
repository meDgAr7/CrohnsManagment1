package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.CrohnsDatabase
import com.example.crohnsmanagment1.data.models.Notes
import com.example.crohnsmanagment1.logic.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application){
    private val repository : NotesRepository
    val getAllNotes : LiveData<List<Notes>>

    init {
        val notesDao = CrohnsDatabase.getDatabase(application).notesDao()
        repository = NotesRepository(notesDao)

        getAllNotes = repository.getAllNotes
    }

    fun addNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNotes(notes)
        }
    }

    fun updateNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNotes(notes)
        }
    }

    fun deleteNotes(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(notes)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}