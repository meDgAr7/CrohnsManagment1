package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Medication
import com.example.crohnsmanagment1.data.models.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query( "SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
}