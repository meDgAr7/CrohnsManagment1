package com.example.crohnsmanagment1.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.crohnsmanagment1.data.database.CrohnsDatabase
import com.example.crohnsmanagment1.data.models.Appointment
import com.example.crohnsmanagment1.logic.repository.AppointmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentViewModel(application: Application) : AndroidViewModel(application){
    private val repository : AppointmentRepository
    val getAllAppointment : LiveData<List<Appointment>>

    init {
        val appointmentDao = CrohnsDatabase.getDatabase(application).appointmentDao()
        repository = AppointmentRepository(appointmentDao)

        getAllAppointment = repository.getAllAppointment
    }

    fun addAppointment(appointment: Appointment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAppointment(appointment)
        }
    }

    fun updateAppointment(appointment: Appointment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAppointment(appointment)
        }
    }

    fun deleteAppointment(appointment: Appointment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAppointment(appointment)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
