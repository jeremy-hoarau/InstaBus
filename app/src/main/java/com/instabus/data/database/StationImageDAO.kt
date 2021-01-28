package com.instabus.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.instabus.data.database.tables.StationImage

@Dao
interface StationImageDAO {
    @Query("SELECT * FROM StationImage WHERE stationId == :stationId")
    fun getAllByStationId(stationId: Int): List<StationImage>

    @Query("SELECT * FROM StationImage WHERE uid == :imageId LIMIT 1")
    fun findByImageId(imageId: Int): StationImage

    @Insert
    fun insertStationImage(image: StationImage)

    @Delete
    fun delete(stationImage: StationImage)
}