package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.CrohnsDatabase
import com.example.crohnsmanagment1.data.models.Medication
import com.example.crohnsmanagment1.logic.repository.MedicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application) : AndroidViewModel(application){
    private val repository : MedicationRepository
    val getAllMedication : LiveData<List<Medication>>

    init {
        val medicationDao = CrohnsDatabase.getDatabase(application).medicationDao()
        repository = MedicationRepository(medicationDao)

        getAllMedication = repository.getAllMedication
    }

    fun addMedication(medication: Medication){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMedication(medication)
        }
    }

    fun updateMedication(medication: Medication){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMedication(medication)
        }
    }

    fun deleteMedication(medication: Medication){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMedication(medication)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}