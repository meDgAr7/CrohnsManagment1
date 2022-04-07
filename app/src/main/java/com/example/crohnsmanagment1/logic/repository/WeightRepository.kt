package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.logic.dao.WeightDao

class WeightRepository(private val weightDao: WeightDao) {
    val getAllWeight: LiveData<List<Weight>> = weightDao.getAllWeight()

    suspend fun addWeight(weight : Weight){
        weightDao.addWeight(weight)
    }

    suspend fun updateWeight(weight : Weight){
        weightDao.updateWeight(weight)
    }

    suspend fun deleteWeight(weight : Weight){
        weightDao.deleteWeight(weight)
    }

    suspend fun deleteAll(){
        weightDao.deleteAll()
    }
}