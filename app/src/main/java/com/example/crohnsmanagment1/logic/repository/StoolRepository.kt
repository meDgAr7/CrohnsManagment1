package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.dao.StoolDao

class StoolRepository(private val stoolDao: StoolDao) {
    val getAllStool: LiveData<List<Stool>> = stoolDao.getAllStool()

    suspend fun addStool(stool : Stool){
        stoolDao.addStool(stool)
    }

    suspend fun updateStool(stool : Stool){
        stoolDao.updateStool(stool)
    }

    suspend fun deleteStool(stool : Stool){
        stoolDao.deleteStool(stool)
    }

    suspend fun deleteAll(){
        stoolDao.deleteAll()
    }
}