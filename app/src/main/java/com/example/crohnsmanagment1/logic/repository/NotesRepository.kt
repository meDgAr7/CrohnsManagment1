package com.example.crohnsmanagment1.logic.repository

import androidx.lifecycle.LiveData
import com.example.crohnsmanagment1.data.models.Appointment
import com.example.crohnsmanagment1.data.models.Notes
import com.example.crohnsmanagment1.logic.dao.AppointmentDao
import com.example.crohnsmanagment1.logic.dao.NotesDao

class NotesRepository  (private val notesDao: NotesDao) {
    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun addNotes(notes : Notes){
        notesDao.addNotes(notes)
    }

    suspend fun updateNotes(notes : Notes){
        notesDao.updateNotes(notes)
    }

    suspend fun deleteNotes(notes : Notes){
        notesDao.deleteNotes(notes)
    }

    suspend fun deleteAll(){
        notesDao.deleteAll()
    }
}