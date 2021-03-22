package com.example.myapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.entity.ImageEntity
import java.util.*

@Dao
interface ImageDao {
    @Query("SELECT * FROM image order by id")
    fun getAll(): LiveData<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repositoryEntity: List<ImageEntity>)

/*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositoryEntity: ImageEntity)
*/

   /*
    @Query("DELETE FROM weather WHERE id= :deleteId")
    suspend fun delete(deleteId: String)

    @Query("DELETE FROM weather")
    suspend fun deleteAll()*/
}