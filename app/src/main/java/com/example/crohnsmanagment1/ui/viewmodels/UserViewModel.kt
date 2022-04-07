package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.CrohnsDatabase
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.logic.repository.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : WeightRepository
    val getAllWeight : LiveData<List<Weight>>

    init {
        val weightDao = CrohnsDatabase.getDatabase(application).weightDao()
        repository = WeightRepository(weightDao)

        getAllWeight = repository.getAllWeight
    }

    fun addWeight(weight: Weight){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWeight(weight)
        }
    }

    fun updateWeight(weight: Weight){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWeight(weight)
        }
    }

    fun deleteWeight(weight: Weight){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWeight(weight)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}