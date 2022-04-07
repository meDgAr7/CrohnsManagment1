package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Medication

@Dao
interface MedicationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMedication(medication: Medication)

    @Update
    suspend fun updateMedication(medication: Medication)

    @Delete
    suspend fun deleteMedication(medication: Medication)

    @Query( "SELECT * FROM medication_table ORDER BY id DESC")
    fun getAllMedication(): LiveData<List<Medication>>

    @Query("DELETE FROM medication_table")
    suspend fun deleteAll()
}