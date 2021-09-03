package com.shiraj.gui.weatherinfo

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shiraj.gui.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherInfoFragmentTest : TestCase() {
    private lateinit var scenario: FragmentScenario<WeatherInfoFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_TDDLearning)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testAddSpend() {
        val temperature = 100
        val cityName = "Bangalore"

        Espresso.onView(withId(R.id.tvCityName))
            .check(ViewAssertions.matches(ViewMatchers.withText(cityName)))

        Espresso.onView(withId(R.id.tvTemp))
            .check(ViewAssertions.matches(ViewMatchers.withText(temperature)))
    }
}