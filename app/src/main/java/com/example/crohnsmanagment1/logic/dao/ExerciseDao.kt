package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Exercise


@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query ( "SELECT * FROM exercise_table ORDER BY id DESC")
    fun getAllExercise(): LiveData<List<Exercise>>

    @Query ("DELETE FROM exercise_table")
    suspend fun deleteAll()


}