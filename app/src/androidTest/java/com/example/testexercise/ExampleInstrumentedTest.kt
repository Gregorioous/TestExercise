package com.example.testexercise

import android.os.SystemClock
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testexercise.view.MainActivity
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.isAssignableFrom(TextView::class.java),
                withId(R.id.usernameEdittext),
                ViewMatchers.withText("Login"),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java)),
                ViewMatchers.withParent(withId(R.id.rootLayout))
            )
        )

        Espresso.onView(
            Matchers.allOf(
                withId(R.id.passwordEditText),
                ViewMatchers.withText("password"),
                ViewMatchers.isAssignableFrom(Button::class.java),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java)),
                ViewMatchers.withParent(withId(R.id.rootLayout))
            )
        )

        Espresso.onView(
            Matchers.allOf(
                withId(R.id.loginbtn),
                ViewMatchers.withText("buttonLogin"),
                ViewMatchers.isAssignableFrom(Button::class.java),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(LinearLayout::class.java)),
                ViewMatchers.withParent(withId(R.id.rootLayout))
            )
        )


        Espresso.onView(withId(R.id.usernameEdittext))
            .perform(ViewActions.replaceText("demo"))
        Espresso.onView(withId(R.id.passwordEditText))
            .perform(ViewActions.replaceText("12345"))

        activityScenarioRule.scenario.recreate()
        Espresso.onView(withId(R.id.usernameEdittext))
            .check(ViewAssertions.matches(ViewMatchers.withText("demo")))
        Espresso.onView(withId(R.id.passwordEditText))
            .check(ViewAssertions.matches(ViewMatchers.withText("12345")))


        Espresso.onView(withId(R.id.loginbtn)).perform(click())

        Espresso.onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        SystemClock.sleep(5000)
        Espresso.onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.recyclerView))
            .check(matches(ViewMatchers.hasMinimumChildCount(1)))
        Espresso.onView(withId(R.id.logoutButton)).perform(click())
    }
    }
