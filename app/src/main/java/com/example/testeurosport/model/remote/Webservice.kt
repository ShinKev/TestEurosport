package com.example.testeurosport.model.remote

import com.example.testeurosport.model.data.WebserviceData
import retrofit2.http.GET

interface Webservice {
    @GET("edfefba")
    suspend fun getData(): WebserviceData
}