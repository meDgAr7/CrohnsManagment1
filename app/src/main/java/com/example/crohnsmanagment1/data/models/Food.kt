package com.example.crohnsmanagment1.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "food_table")
data class Food(@PrimaryKey(autoGenerate = true)

    val id: Int,
    val food_title: String,
    val food_description: String,
    val food_starTime: String,
    //val imageId: Int,
    ) : Parcelable



