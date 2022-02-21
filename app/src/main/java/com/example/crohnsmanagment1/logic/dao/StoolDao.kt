package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Stool

@Dao
interface StoolDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStool(stool: Stool)

    @Update
    suspend fun updateStool(stool: Stool)

    @Delete
    suspend fun deleteStool(stool: Stool)

    @Query ( "SELECT * FROM stool_table ORDER BY id DESC")
    fun getAllStool(): LiveData<List<Stool>>

    @Query ("DELETE FROM stool_table")
    suspend fun deleteAll()


}