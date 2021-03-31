package com.example.testeurosport.model.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class WebserviceData(
    val videos: List<Video>,
    val stories: List<Story>
)
