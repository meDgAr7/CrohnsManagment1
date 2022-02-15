package com.example.crohnsmanagment1.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "exercise_table")
data class Exercise(@PrimaryKey(autoGenerate = true)

                val id: Int,
                val exercise_title: String,
                val exercise_description: String,
                val exercise_starTime: String,
    //val imageId: Int,
) : Parcelable


