package com.example.crohnsmanagment1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crohnsmanagment1.data.models.Exercise
import com.example.crohnsmanagment1.logic.dao.ExerciseDao
import java.lang.NullPointerException
import java.security.AccessControlContext

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao() : ExerciseDao

    companion object{
        @Volatile
        private var INSTANCE: ExerciseDatabase? = null

        fun getDatabase(context: Context): ExerciseDatabase{
            val tempInstance :ExerciseDatabase? = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDatabase::class.java,
                    "exercise_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}