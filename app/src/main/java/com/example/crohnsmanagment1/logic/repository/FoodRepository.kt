package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.logic.dao.FoodDao

class FoodRepository(private val foodDao: FoodDao) {
    val getAllFood: LiveData<List<Food>> = foodDao.getAllFood()

    suspend fun addFood(food : Food){
        foodDao.addFood(food)
    }

    suspend fun updateFood(food : Food){
        foodDao.updateFood(food)
    }

    suspend fun deleteFood(food : Food){
        foodDao.deleteFood(food)
    }

    suspend fun deleteAll(){
        foodDao.deleteAll()
    }
}