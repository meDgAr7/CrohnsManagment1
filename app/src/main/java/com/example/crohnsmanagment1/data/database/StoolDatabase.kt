package com.example.crohnsmanagment1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crohnsmanagment1.data.models.Stool
import com.example.crohnsmanagment1.logic.dao.StoolDao
import java.lang.NullPointerException
import java.security.AccessControlContext

@Database(entities = [Stool::class], version = 1, exportSchema = false)
abstract class StoolDatabase : RoomDatabase() {
    abstract fun stoolDao() : StoolDao

    companion object{
        @Volatile
        private var INSTANCE: StoolDatabase? = null

        fun getDatabase(context: Context): StoolDatabase{
            val tempInstance :StoolDatabase? = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    StoolDatabase::class.java,
                    "stool_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}