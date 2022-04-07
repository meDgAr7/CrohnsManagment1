package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.CrohnsDatabase
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.repository.StoolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StoolViewModel(application: Application) : AndroidViewModel(application){
    private val repository : StoolRepository
    val getAllStool : LiveData<List<Stool>>

    init {
        val stoolDao = CrohnsDatabase.getDatabase(application).stoolDao()
        repository = StoolRepository(stoolDao)

        getAllStool = repository.getAllStool
    }

    fun addStool(stool: Stool){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStool(stool)
        }
    }

    fun updateStool(stool: Stool){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStool(stool)
        }
    }

    fun deleteStool(stool: Stool){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStool(stool)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}