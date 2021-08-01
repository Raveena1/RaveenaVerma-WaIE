
package com.example.myapplicationwalmart.repository

import com.example.myapplicationwalmart.network.ApodApi

class ApodRepository(
    private val api: ApodApi,

    ) : BaseRepository() {

    suspend fun getApodData(

    ) = safeApiCall {
        api.getApodData()
    }


}
