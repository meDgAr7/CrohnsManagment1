package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.ExerciseDatabase
import com.example.crohnsmanagment1.data.database.FoodDatabase
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.logic.dao.FoodDao
import com.example.crohnsmanagment1.logic.repository.ExerciseRepository
import com.example.crohnsmanagment1.logic.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExerciseViewModel(application: Application) : AndroidViewModel(application){
    private val repository : ExerciseRepository
    val getAllExercise : LiveData<List<Exercise>>

    init {
        val exerciseDao = ExerciseDatabase.getDatabase(application).exerciseDao()
        repository = ExerciseRepository(exerciseDao)

        getAllExercise = repository.getAllExercise
    }

    fun addExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExercise(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExercise(exercise)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
