package com.example.crohnsmanagment1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crohnsmanagment1.data.models.*
import com.example.crohnsmanagment1.logic.dao.*

@Database(entities = [Food::class,
                    Exercise::class,
                    Mood::class,
                    Stool::class,
                    Weight::class,
                     Medication::class,
                     Appointment::class,
                     Notes::class],
    version = 1, exportSchema = false)
abstract class CrohnsDatabase : RoomDatabase() {
    abstract fun foodDao() : FoodDao
    abstract fun exerciseDao() : ExerciseDao
    abstract fun moodDao() : MoodDao
    abstract fun stoolDao() : StoolDao
    abstract fun weightDao() : WeightDao
    abstract fun appointmentDao() : AppointmentDao
    abstract fun medicationDao() : MedicationDao
    abstract fun notesDao() : NotesDao

    companion object{
        @Volatile
        private var INSTANCE: CrohnsDatabase? = null

        fun getDatabase(context: Context): CrohnsDatabase {
            val tempInstance : CrohnsDatabase? = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    CrohnsDatabase::class.java,
                    "crohns_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}