package com.crickbuzz.balvier.medialert;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.crickbuzz.balvier.medialert.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;

/**
 * Created by Balvier on 9/17/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork() {
        Espresso.onView(withHint(R.string.medicineName))
                .perform(typeText("bvc"), closeSoftKeyboard());
        Espresso.onView(withHint(R.string.medicineDasages))
                .perform(typeText("3"), closeSoftKeyboard());

        Espresso.onView(withResourceName("buttomSave"))
                .check(matches(isDisplayed()));

        Espresso.onView(withResourceName("madicineDate"))
                .check(matches(isDisplayed()));
        Espresso.onView(withResourceName("madicineTime"))
                .check(matches(isDisplayed()));
        Espresso.onView(withResourceName("buttomShowAllMissedReport"))
                .check(matches(isDisplayed()));

        Espresso.onView(withResourceName("buttomShowAllMissedReport"))
                .perform(click());
        try {
            wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
