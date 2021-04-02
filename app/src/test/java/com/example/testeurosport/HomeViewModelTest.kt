package com.example.testeurosport

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testeurosport.model.DataRepository
import com.example.testeurosport.model.data.*
import com.example.testeurosport.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @MockK
    private lateinit var dataRepository: DataRepository

    private lateinit var homeViewModel: HomeViewModel

    private val story = Story(
        id = 2,
        title = "TestStory",
        teaser = "TestTeaser",
        image = "http://test.image",
        date = 110000.0,
        author = "TestAuthor",
        sport = Sport(id = 20, name = "TestSport2")
    )
    private val video = Video(
        id = 1,
        title = "TestVideo",
        thumb = "http://test.thumb",
        url = "http://test.url",
        date = 100000.0,
        sport = Sport(id = 10, name = "TestSport1")
    )
    private val webserviceData: WebserviceData = WebserviceData(listOf(video), listOf(story))
    private val articleList: List<Article> = listOf(story, video)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(dataRepository)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `when calling getArticleList, value is correct`() {
        // given
        coEvery { dataRepository.getData() } returns webserviceData

        // when
        homeViewModel.getArticleList()

        // then
        assertEquals(articleList, homeViewModel.getArticleList().value)
    }
}