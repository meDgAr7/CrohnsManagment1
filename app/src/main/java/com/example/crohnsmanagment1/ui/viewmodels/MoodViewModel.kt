package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.CrohnsDatabase
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.logic.dao.MoodDao
import com.example.crohnsmanagment1.logic.repository.MoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MoodViewModel(application: Application) : AndroidViewModel(application){
    private val repository : MoodRepository
    val getAllMood : LiveData<List<Mood>>

    init {
        val moodDao = CrohnsDatabase.getDatabase(application).moodDao()
        repository = MoodRepository(moodDao)

        getAllMood = repository.getAllMood
    }

    fun addMood(mood: Mood){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMood(mood)
        }
    }

    fun updateMood(mood: Mood){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMood(mood)
        }
    }

    fun deleteMood(mood: Mood){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMood(mood)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}