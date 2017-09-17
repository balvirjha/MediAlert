package com.crickbuzz.balvier.medialert;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.crickbuzz.balvier.medialert.view.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FlowTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void flowTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.madicineName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("fgh"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.madicineDasages),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("4"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.madicineDate),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.madicineTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.madicineName), withText("fgh"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.madicineDasages), withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.buttomSave),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                3),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buttomSave),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.madicineName), withText("Medicine name"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("Medicine name")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.madicineDasages), withText("How many tablets a day(Numbers only)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.leftFragment),
                                        0),
                                1),
                        isDisplayed()));
        editText4.check(matches(withText("How many tablets a day(Numbers only)")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
