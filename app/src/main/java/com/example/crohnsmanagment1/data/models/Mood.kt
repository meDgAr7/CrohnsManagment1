package com.example.crohnsmanagment1.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "mood_table")
data class Mood(@PrimaryKey(autoGenerate = true)

                val id: Int,
                val mood_title: String,
                val mood_description: String,
                val mood_starTime: String,
    //val imageId: Int,
) : Parcelable

