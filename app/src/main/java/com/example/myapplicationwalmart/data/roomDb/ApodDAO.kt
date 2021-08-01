package com.example.walmartproject.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ApodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApodData(data : ApodEntity)
    @Update
    suspend fun updateApodData(data : ApodEntity)

    @Delete
    suspend fun deleteApodData(data : ApodEntity)

    @Query("DELETE from apod_database")
    suspend fun deleteAll()

    @Query("SELECT * from apod_database")
    fun getCompleteData() : LiveData<List<ApodEntity>>
}