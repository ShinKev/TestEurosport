package com.example.testeurosport.model

import com.example.testeurosport.model.data.WebserviceData
import com.example.testeurosport.model.remote.RetrofitClient

class DataRepository {
    private val webservice = RetrofitClient.webservice

    suspend fun getData(): WebserviceData = webservice.getData()
}