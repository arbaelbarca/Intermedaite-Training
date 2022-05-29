package com.arbaelbarca.trainingclassintermediate

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun initApiService(): ApiService {
            val httpLoging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoging)
                .build()

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://web-api-test1.herokuapp.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofitBuilder.create(ApiService::class.java)
        }
    }

}