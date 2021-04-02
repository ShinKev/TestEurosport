package com.example.testeurosport.model.remote

import com.example.testeurosport.EurosportApplication
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitClient(context: EurosportApplication) {
    private val rootUrl = "https://extendsclass.com/api/json-storage/bin/"
    private val jacksonObjectMapper = jacksonObjectMapper()
    private val okHttpClient = CacheManager.getOkHttpClient(context)

    val webservice: Webservice by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(rootUrl)
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper))
            .build()
            .create(Webservice::class.java)
    }
}