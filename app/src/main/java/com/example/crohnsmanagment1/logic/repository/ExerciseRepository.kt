package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.logic.dao.ExerciseDao


class ExerciseRepository(private val exerciseDao: ExerciseDao) {
    val getAllExercise: LiveData<List<Exercise>> = exerciseDao.getAllExercise()

    suspend fun addExercise(exercise : Exercise){
        exerciseDao.addExercise(exercise)
    }

    suspend fun updateExercise(exercise : Exercise){
        exerciseDao.updateExercise(exercise)
    }

    suspend fun deleteExercise(exercise : Exercise){
        exerciseDao.deleteExercise(exercise)
    }

    suspend fun deleteAll(){
        exerciseDao.deleteAll()
    }
}