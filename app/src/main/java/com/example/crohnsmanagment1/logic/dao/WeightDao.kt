package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Weight


@Dao
interface WeightDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWeight(weight : Weight)

    @Update
    suspend fun updateWeight(weight : Weight)

    @Delete
    suspend fun deleteWeight(weight : Weight)

    @Query( "SELECT * FROM weight_table ORDER BY id DESC")
    fun getAllWeight(): LiveData<List<Weight>>

    @Query("DELETE FROM weight_table")
    suspend fun deleteAll()
}