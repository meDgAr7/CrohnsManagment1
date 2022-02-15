package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.logic.dao.MoodDao

class MoodRepository(private val moodDao: MoodDao) {
    val getAllMood: LiveData<List<Mood>> = moodDao.getAllMood()

    suspend fun addMood(mood : Mood){
        moodDao.addMood(mood)
    }

    suspend fun updateMood(mood : Mood){
        moodDao.updateMood(mood)
    }

    suspend fun deleteMood(mood : Mood){
        moodDao.deleteMood(mood)
    }

    suspend fun deleteAll(){
        moodDao.deleteAll()
    }
}