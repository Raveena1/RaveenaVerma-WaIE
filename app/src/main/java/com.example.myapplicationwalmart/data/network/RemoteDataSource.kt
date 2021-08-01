package com.example.myapplicationwalmart.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        // Base Url after generation of API KEY to access NASA Open source API's
        // API Key generated to access data :
        // api_key=Lfm2cMoBOrhMuvwamzs0W1m3zz0alFZhOpp6kdA3
        val BASE_URL = "https://api.nasa.gov/planetary/"
    }

     fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                        }
                            .build())
                    }.also { client ->
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create()).build().create(api)


    }


}