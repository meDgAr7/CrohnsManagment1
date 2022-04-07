package com.example.crohnsmanagment1.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Notes(@PrimaryKey(autoGenerate = true)

                    val id: Int,
                    val notes_title: String,
                    val notes_body: String,
                    val notes_timeStamp: String,

    //val imageId: Int,
) : Parcelable