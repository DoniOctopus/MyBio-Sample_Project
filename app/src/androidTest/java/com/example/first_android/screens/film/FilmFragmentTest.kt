package com.example.first_android.screens.film

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.first_android.MainActivity
import com.example.first_android.R
import com.example.first_android.film.FilmViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilmFragmentTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )

    @Test
    fun mainActivity_onLaunch_success() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.frameLayout5))
            .check(matches(isDisplayed()))
    }

    @Test
    fun mainActivity_onFilmItemClick_showFilmDetail() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.filmsRecycleView))
            .perform(actionOnItemAtPosition<FilmViewHolder>(1, click()))
        Espresso.onView(withId(R.id.frameLayout3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.duration_film_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun mainActivity_onFilmItemClickAndBackPressed() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.filmsRecycleView))
            .perform(actionOnItemAtPosition<FilmViewHolder>(1, click()))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.frameLayout5))
            .check(matches(isDisplayed()))
    }

    @Test
    fun mainActivity_onCreateFilmTabClick() {
        val bottomNavigationItemView = Espresso.onView(withId(R.id.createFilm))
        bottomNavigationItemView.perform(click())
        val frameLayout = Espresso.onView(withId(R.id.frameLayout4))
        frameLayout.check(matches(isDisplayed()))
    }
}