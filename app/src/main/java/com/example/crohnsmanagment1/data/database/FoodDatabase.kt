package com.example.crohnsmanagment1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crohnsmanagment1.data.models.Food
import com.example.crohnsmanagment1.logic.dao.FoodDao
import java.lang.NullPointerException
import java.security.AccessControlContext

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao() : FoodDao

    companion object{
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context: Context): FoodDatabase{
            val tempInstance :FoodDatabase? = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "food_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}