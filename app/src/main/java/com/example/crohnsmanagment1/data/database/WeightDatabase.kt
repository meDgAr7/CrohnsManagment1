package com.example.crohnsmanagment1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crohnsmanagment1.data.models.Weight
import com.example.crohnsmanagment1.logic.dao.WeightDao

@Database(entities = [Weight::class], version = 1, exportSchema = false)
abstract class WeightDatabase : RoomDatabase (){
    abstract fun weightDao() : WeightDao

    companion object{
        @Volatile
        private var INSTANCE: WeightDatabase? = null

        fun getDatabase(context: Context): WeightDatabase{
            val tempInstance : WeightDatabase? = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    WeightDatabase::class.java,
                    "weight_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
