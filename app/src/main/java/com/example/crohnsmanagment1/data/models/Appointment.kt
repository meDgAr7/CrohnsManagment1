package com.example.crohnsmanagment1.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "appointment_table")
data class Appointment(@PrimaryKey(autoGenerate = true)

                 val id: Int,
                val appointment_type: String,
                val appointment_dr: String,
                val appointment_timeStamp: String,
                val appointment_location: String

    //val imageId: Int,
) : Parcelable