package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Appointment
import com.example.crohnsmanagment1.logic.dao.AppointmentDao

class AppointmentRepository (private val appointmentDao: AppointmentDao) {
    val getAllAppointment: LiveData<List<Appointment>> = appointmentDao.getAllAppointment()

    suspend fun addAppointment(appointment : Appointment){
        appointmentDao.addAppointment(appointment)
    }

    suspend fun updateAppointment(appointment : Appointment){
        appointmentDao.updateAppointment(appointment)
    }

    suspend fun deleteAppointment(appointment : Appointment){
        appointmentDao.deleteAppointment(appointment)
    }

    suspend fun deleteAll(){
        appointmentDao.deleteAll()
    }
}