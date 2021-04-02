package com.example.testeurosport

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.testeurosport.model.data.Sport
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.view.ui.StoryFragment
import org.junit.Test

class StoryFragmentTest {

    private val story = Story(
        id = 1,
        title = "TestStory",
        teaser = "TestTeaser",
        image = "http://test.image",
        date = 110000.0,
        author = "TestAuthor",
        sport = Sport(id = 20, name = "TestSport2")
    )

    @Test
    fun bundleIsCorrectlySetToView() {
        // when
        launchFragmentInContainer<StoryFragment>(bundleOf("story" to story))

        // then
        onView(withId(R.id.title_detail_text)).check(ViewAssertions.matches(withText("TestStory")))
        onView(withId(R.id.story_detail_text)).check(ViewAssertions.matches(withText("TestTeaser")))
    }
}