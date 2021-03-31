package com.example.testeurosport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeurosport.model.DataRepository
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import kotlinx.coroutines.launch

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val videoListLiveData = MutableLiveData<List<Video>>()
    private val storyListLiveData = MutableLiveData<List<Story>>()

    fun loadData() {
        viewModelScope.launch {
            val webserviceData = dataRepository.getData()
            videoListLiveData.postValue(webserviceData.videos)
            storyListLiveData.postValue(webserviceData.stories)
        }
    }

    fun getVideoList(): LiveData<List<Video>> = videoListLiveData
    fun getStoryList(): LiveData<List<Story>> = storyListLiveData
}