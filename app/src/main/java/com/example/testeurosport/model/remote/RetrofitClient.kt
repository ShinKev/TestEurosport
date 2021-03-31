package com.example.testeurosport.model.remote

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {
    private const val rootUrl = "https://static.leboncoin.fr/img/shared/"
    private val jacksonObjectMapper = jacksonObjectMapper()
    private val okHttpClient = OkHttpClient()

    val webservice: Webservice by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(rootUrl)
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper))
            .build()
            .create(Webservice::class.java)
    }
}