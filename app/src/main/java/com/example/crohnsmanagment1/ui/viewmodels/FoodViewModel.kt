package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.FoodDatabase
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.logic.dao.FoodDao
import com.example.crohnsmanagment1.logic.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FoodViewModel(application: Application) : AndroidViewModel(application){
    private val repository : FoodRepository
    val getAllFood : LiveData<List<Food>>

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)

        getAllFood = repository.getAllFood
    }

    fun addFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(food)
        }
    }

    fun updateFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFood(food)
        }
    }

    fun deleteFood(food: Food){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFood(food)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
