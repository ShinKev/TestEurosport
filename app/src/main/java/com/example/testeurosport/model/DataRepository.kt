package com.example.testeurosport.model

import com.example.testeurosport.model.data.WebserviceData
import com.example.testeurosport.model.remote.RetrofitClient

class DataRepository(private val retrofitClient: RetrofitClient) {
    private val webservice = retrofitClient.webservice

    suspend fun getData(): WebserviceData = webservice.getData()
}