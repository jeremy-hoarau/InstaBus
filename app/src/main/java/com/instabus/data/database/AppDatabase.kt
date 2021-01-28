package com.instabus.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.instabus.data.database.tables.StationImage

@Database(entities = [StationImage::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract fun stationImageDAO(): StationImageDAO
}