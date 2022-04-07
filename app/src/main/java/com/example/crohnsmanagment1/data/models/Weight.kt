package com.example.crohnsmanagment1.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "weight_table")
data class Weight(@PrimaryKey(autoGenerate = true)
                  val id: Int,
                  val weight_kg: Float,
                  val weight_date: String

) : Parcelable