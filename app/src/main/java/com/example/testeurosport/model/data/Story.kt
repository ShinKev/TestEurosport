package com.example.testeurosport.model.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Story(
    val id: Long = 0,
    val title: String = "",
    val teaser: String = "",
    val image: String = "",
    val date: Double = 0.0,
    val author: String = "",
    val sport: Sport? = null
)
