package com.example.testeurosport.model.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Video(
    val id: Long = 0,
    val title: String = "",
    val thumb: String = "",
    val url: String = "",
    val date: Double = 0.0,
    val sport: Sport? = null,
    val views: Long = 0
)
