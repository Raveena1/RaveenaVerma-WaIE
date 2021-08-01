package com.example.myapplicationwalmart.network

import com.example.myapplicationwalmart.responses.APODData
import retrofit2.http.GET

interface ApodApi {
    @GET("apod?api_key=Lfm2cMoBOrhMuvwamzs0W1m3zz0alFZhOpp6kdA3")
    suspend fun getApodData(
    ): APODData

}