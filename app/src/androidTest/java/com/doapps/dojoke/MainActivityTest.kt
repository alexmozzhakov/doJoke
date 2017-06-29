package com.doapps.dojoke

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val frameLayout = onView(allOf(childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()))
        val frameLayout2 = onView(allOf(childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()))
        val frameLayout3 = onView(allOf(childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()))
        val frameLayout4 = onView(allOf(childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()))
        frameLayout.perform(click())
        frameLayout2.perform(click())
        frameLayout.perform(click())
        frameLayout3.perform(click())
        frameLayout3.perform(click())
        frameLayout2.perform(click())
        frameLayout4.perform(click())
        frameLayout4.perform(click())
    }

    private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
