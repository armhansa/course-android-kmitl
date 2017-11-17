package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    ViewInteraction name = onView(withId(R.id.editTExtName));
    ViewInteraction age = onView(withId(R.id.editTextAge));
    ViewInteraction added = onView(withId(R.id.buttonAdded));
    ViewInteraction goToList = onView(withId(R.id.buttonGotoList));
    ViewInteraction clearList = onView(withId(R.id.btnChearList));

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void mainActivityTest1() {
        SystemClock.sleep(1000);
        added.perform(click());
        checkFeedback("Please Enter user info");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest2() {
        SystemClock.sleep(1000);
        age.perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        checkFeedback("Please Enter user info");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest3() {
        SystemClock.sleep(1000);
        goToList.perform(click());
        clearList.perform(click());
        checkFeedback("Not Found");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest4() {
        SystemClock.sleep(1000);
        name.perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        checkFeedback("Please Enter user info");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest5() {
        SystemClock.sleep(1000);
        name.perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        age.perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        SystemClock.sleep(1000);
        goToList.perform(click());
        checkList(0, "Ying");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest6() {
        SystemClock.sleep(1000);
        name.perform(replaceText("Ladarat"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        age.perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        SystemClock.sleep(1000);
        goToList.perform(click());
        checkList(1, "Ladarat");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest7() {
        SystemClock.sleep(1000);
        name.perform(replaceText("Somkait"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        age.perform(replaceText("80"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        SystemClock.sleep(1000);
        goToList.perform(click());
        checkList(2, "Somkait");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest8() {
        SystemClock.sleep(1000);
        name.perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        age.perform(replaceText("60"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        SystemClock.sleep(1000);
        goToList.perform(click());
        checkList(3, "Prayoch");
        SystemClock.sleep(5000);
        pressBack();
    }

    @Test
    public void mainActivityTest9() {
        SystemClock.sleep(1000);
        name.perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        age.perform(replaceText("50"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        added.perform(click());
        SystemClock.sleep(1000);
        goToList.perform(click());

        SystemClock.sleep(5000);
        pressBack();
    }

    private void checkFeedback(String keyWord) {
        onView(withText(keyWord)).check(matches(isDisplayed()));
    }

    private void checkList(int position, String name) {
        onView(withRecyclerView(R.id.list).atPositionOnView(position, R.id.textName)).check(matches(withText(name)));
    }

}
