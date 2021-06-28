package com.undeadcoder.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.undeadcoder.moviecatalogue.R
import com.undeadcoder.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.banner)).check(matches(isDisplayed()))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.genre)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTVShows() {
        onView(withId(R.id.tvShowsFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withId(R.id.tvShowsFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.banner)).check(matches(isDisplayed()))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.genre)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rv_movies)).perform(click())
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favoritesFragment)).perform(click())
        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.banner)).check(matches(isDisplayed()))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.genre)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTvShows() {
        onView(withId(R.id.tvShowsFragment)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favoritesFragment)).perform(click())
        onView(ViewMatchers.withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_favorite_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.banner)).check(matches(isDisplayed()))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.genre)).check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
    }

}