package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.helpers.JokeTellingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * JokeTellingApp Created by aidan on 22/07/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private JokeTellingIdlingResource jokeTellingIdlingResource;

    @Before
    public void registerIdlingResource() {
        jokeTellingIdlingResource = mainActivityActivityTestRule.getActivity().getJokeTellingIdlingResource();
        IdlingRegistry.getInstance().register(jokeTellingIdlingResource);
    }

    @Test
    public void testJokeStringNotEmpty() {
        onView(withId(R.id.btn_tell_joke)).perform(click());
        onView(withId(R.id.tv_display_joke)).check(matches(not(withText(""))));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(jokeTellingIdlingResource);
    }

}