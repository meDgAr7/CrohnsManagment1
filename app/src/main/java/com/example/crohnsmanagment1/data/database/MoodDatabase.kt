package com.example.crohnsmanagment1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crohnsmanagment1.data.models.Mood
import com.example.crohnsmanagment1.logic.dao.MoodDao
import java.lang.NullPointerException
import java.security.AccessControlContext

@Database(entities = [Mood::class], version = 1, exportSchema = false)
abstract class MoodDatabase : RoomDatabase() {
    abstract fun moodDao() : MoodDao

    companion object{
        @Volatile
        private var INSTANCE: MoodDatabase? = null

        fun getDatabase(context: Context): MoodDatabase{
            val tempInstance :MoodDatabase? = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    MoodDatabase::class.java,
                    "mood_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}