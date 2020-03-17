package io.mellouk.usershub

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import io.mellouk.common_android.utils.UsersIdlingResource
import io.mellouk.main.MainActivity
import io.mellouk.usershub.macthers.RecyclerViewMatcher.withRecyclerView
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    private lateinit var usersIdlingResource: UsersIdlingResource

    @Before
    fun setup() {
        val app = activityTestRule.activity.application as UsersHubApp
        usersIdlingResource = app.appComponent.getIdlingResource()

        IdlingRegistry.getInstance().register(usersIdlingResource)
    }

    @After
    fun release() {
        IdlingRegistry.getInstance().unregister(usersIdlingResource)
    }

    @Test
    fun onStartActivity_ShouldLoadUsers() {
        onView(withId(R.id.rvUsers))
            .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.rvUsers).atPosition(0))
            .check(matches(hasDescendant(withText("defunkt"))));
    }

    @Test
    fun onStartActivity_ShouldOpenUserProfile() {
        onView(withId(R.id.rvUsers))
            .check(matches(isDisplayed()));

        onView(withId(R.id.rvUsers))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));

        onView(withId(R.id.ivUser))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvName))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvSummary))
            .check(matches(isDisplayed()))
    }
}