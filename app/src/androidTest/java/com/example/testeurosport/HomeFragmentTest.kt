package com.example.testeurosport

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Sport
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.view.ui.HomeFragment
import com.example.testeurosport.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest : KoinTest {

    @RelaxedMockK
    private lateinit var homeViewModel: HomeViewModel
    private val appTestModule = module {
        single { homeViewModel }
    }

    private val story = Story(
        id = 1,
        title = "TestStory",
        teaser = "TestTeaser",
        image = "http://test.image",
        date = 110000.0,
        author = "TestAuthor",
        sport = Sport(id = 20, name = "TestSport2")
    )
    private val video = Video(
        id = 2,
        title = "TestVideo",
        thumb = "http://test.thumb",
        url = "http://test.url",
        date = 100000.0,
        sport = Sport(id = 10, name = "TestSport1")
    )
    private val articleList: List<Article> = listOf(story, video)
    private val articleListMutableLiveData = MutableLiveData<List<Article>>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        loadKoinModules(appTestModule)

        // given
        every { homeViewModel.getArticleList() } returns articleListMutableLiveData
    }

    @After
    fun clearUp() {
        unloadKoinModules(appTestModule)
    }

    @Test
    fun getArticleListCalledWhenFragmentIsLaunched() {
        // when
        launchFragmentInContainer<HomeFragment>()

        // then
        verify(exactly = 1) { homeViewModel.getArticleList() }
    }

    @Test
    fun dataIsCorrectlySetInViews() {
        // when
        launchFragmentInContainer<HomeFragment>()
        articleListMutableLiveData.postValue(articleList)

        // then
        onView(withId(R.id.title_story_text)).check(matches(withText("TestStory")))
        onView(withId(R.id.title_video_text)).check(matches(withText("TestVideo")))
    }

    @Test
    fun testNavigationToStoryFragment() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        runOnUiThread { navController.setGraph(R.navigation.nav_graph) }

        // when
        val titleScenario = launchFragmentInContainer<HomeFragment>()
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        articleListMutableLiveData.postValue(articleList)

        // then
        onView(withId(R.id.story_image)).perform(ViewActions.click())
        assert(navController.currentDestination?.id == R.id.storyFragment)
    }
}