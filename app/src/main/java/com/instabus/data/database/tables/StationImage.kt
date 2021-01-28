package com.instabus.data.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationImage(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val stationId: Int,
    val title: String,
    val image: ByteArray
)
