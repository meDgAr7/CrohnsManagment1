package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Medication
import com.example.crohnsmanagment1.logic.dao.MedicationDao

class MedicationRepository (private val medicationDao: MedicationDao) {
    val getAllMedication: LiveData<List<Medication>> = medicationDao.getAllMedication()

    suspend fun addMedication(medication : Medication){
        medicationDao.addMedication(medication)
    }

    suspend fun updateMedication(medication : Medication){
        medicationDao.updateMedication(medication)
    }

    suspend fun deleteMedication(medication : Medication){
        medicationDao.deleteMedication(medication)
    }

    suspend fun deleteAll(){
        medicationDao.deleteAll()
    }
}