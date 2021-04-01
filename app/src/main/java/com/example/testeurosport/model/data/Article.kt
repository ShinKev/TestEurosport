package com.example.testeurosport.model.data

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlinx.parcelize.Parcelize

sealed class Article

@JsonIgnoreProperties(ignoreUnknown = true)
data class Video(
    val id: Long = 0,
    val title: String = "",
    val thumb: String = "",
    val url: String = "",
    val date: Double = 0.0,
    val sport: Sport? = null,
    val views: Long = 0
) : Article()

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Story(
    val id: Long = 0,
    val title: String = "",
    val teaser: String = "",
    val image: String = "",
    val date: Double = 0.0,
    val author: String = "",
    val sport: Sport? = null
) : Article(), Parcelable

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Sport(
    val id: Long = 0,
    val name: String = ""
) : Parcelable