package com.example.walmartproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod_database")
data class ApodEntity (

    @PrimaryKey()
    @ColumnInfo(name = "date")
    val date : String,

    @ColumnInfo(name = "apod_title_name")
    val title: String,

    @ColumnInfo(name = "apod_image_expln")
    val explanation : String,

    @ColumnInfo(name = "apod_image_url")
    val imageurl : String
)