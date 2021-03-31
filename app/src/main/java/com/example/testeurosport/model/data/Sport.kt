package com.example.testeurosport.model.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Sport(
    val id: Long = 0,
    val name: String = ""
)
