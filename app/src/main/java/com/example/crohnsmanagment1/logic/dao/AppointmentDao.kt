package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Appointment

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAppointment(appointment: Appointment)

    @Update
    suspend fun updateAppointment(appointment: Appointment)

    @Delete
    suspend fun deleteAppointment(appointment: Appointment)

    @Query( "SELECT * FROM appointment_table ORDER BY id DESC")
    fun getAllAppointment(): LiveData<List<Appointment>>

    @Query("DELETE FROM appointment_table")
    suspend fun deleteAll()
}