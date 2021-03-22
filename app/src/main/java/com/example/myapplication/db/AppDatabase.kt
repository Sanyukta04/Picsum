package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.dao.ImageDao
import com.example.myapplication.db.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val imageDao: ImageDao
}


