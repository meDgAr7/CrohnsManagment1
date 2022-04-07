package com.example.crohnsmanagment1.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "medication_table")
data class Medication(@PrimaryKey(autoGenerate = true)

                 val id: Int,

    //val imageId: Int,
) : Parcelable