package com.example.crohnsmanagment1.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "stool_table")
data class Stool(@PrimaryKey(autoGenerate = true)

                val id: Int,
                val stool_title: String,
                val stool_description: String,
                val stool_starTime: String,
    //val imageId: Int,
) : Parcelable
