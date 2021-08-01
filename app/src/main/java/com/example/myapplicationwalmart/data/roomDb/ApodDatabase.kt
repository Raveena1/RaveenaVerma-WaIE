package com.example.walmartproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase : RoomDatabase(){

    abstract val apodDao : ApodDAO

    companion object{
        @Volatile
        private var INSTANCE : ApodDatabase ?= null

        fun getInstance(context: Context): ApodDatabase {
            synchronized(this) {
                var instance : ApodDatabase ?= INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                    ApodDatabase::class.java,
                    "apod_database").build()
                }
                return instance
            }


        }
    }
}