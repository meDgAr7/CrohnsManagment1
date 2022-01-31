package com.example.crohnsmanagment1.logic.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crohnsmanagment1.data.models.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFood(food: Food)

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query ( "SELECT * FROM food_table ORDER BY id DESC")
    fun getAllFood(): LiveData<List<Food>>

    @Query ("DELETE FROM food_table")
    suspend fun deleteAll()


}