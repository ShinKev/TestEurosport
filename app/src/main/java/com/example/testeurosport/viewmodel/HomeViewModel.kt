package com.example.testeurosport.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeurosport.model.DataRepository
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.model.data.WebserviceData
import kotlinx.coroutines.launch

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private var webserviceData: WebserviceData? = null
    private val articleListLiveData = MutableLiveData<List<Article>>()

    private fun loadData() {
        viewModelScope.launch {
            webserviceData = dataRepository.getData()
            articleListLiveData.postValue(processData(webserviceData))
        }
    }

    private fun processData(webserviceData: WebserviceData?): List<Article> {
        if (webserviceData == null) return listOf()

        val videoList = webserviceData.videos as MutableList<Video>
        val storyList = webserviceData.stories as MutableList<Story>

        storyList.sortByDescending { it.date }
        videoList.sortByDescending { it.date }

        val articleList = mutableListOf<Article>()
        articleList.addAll(storyList)
        var index = 1
        videoList.forEach {
            articleList.add(index, it)
            index += 2
        }

        return articleList
    }

    fun getArticleList(): LiveData<List<Article>> {
        if (webserviceData == null) loadData()
        return articleListLiveData
    }

}