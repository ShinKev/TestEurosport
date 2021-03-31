package com.example.testeurosport

import com.example.testeurosport.model.DataRepository
import com.example.testeurosport.model.data.Sport
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.model.data.WebserviceData
import com.example.testeurosport.model.remote.RetrofitClient
import com.example.testeurosport.model.remote.Webservice
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DataRepositoryTest {

    @MockK
    private lateinit var webservice: Webservice

    private lateinit var dataRepository: DataRepository

    private val webserviceData = WebserviceData(
        videos = listOf(Video(
            id = 1,
            title = "TestVideo",
            thumb = "http://test.thumb",
            url = "http://test.url",
            date = 100000.0,
            sport = Sport(id = 10, name = "TestSport1")
        )),
        stories = listOf(Story(
            id = 2,
            title = "TestStory",
            teaser = "TestTeaser",
            image = "http://test.image",
            date = 110000.0,
            author = "TestAuthor",
            sport = Sport(id = 20, name = "TestSport2")
        ))
    )

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        mockkObject(RetrofitClient)
        every { RetrofitClient.webservice } returns webservice
        dataRepository = DataRepository()
    }

    @Test
    fun `getData function from DataRepository uses the API call from Webservice`() {
        var dataFromDataRepository: WebserviceData

        // given
        coEvery { webservice.getData() } returns webserviceData

        // when
        runBlocking { dataFromDataRepository = dataRepository.getData() }

        // then
        coVerify(exactly = 1) { webservice.getData() }
        assertEquals(dataFromDataRepository, webserviceData)
    }
}