package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    ViewInteraction nameInput = onView(withId(R.id.editTExtName));
    ViewInteraction ageInput = onView(withId(R.id.editTextAge));
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
        added.perform(click());
        checkFeedback("Please Enter user info");
    }

    @Test
    public void mainActivityTest2() {
        setAgeInput("20");
        added.perform(click());
        checkFeedback("Please Enter user info");
    }

    @Test
    public void mainActivityTest3() {
        clearList();

        goToList.perform(click());
        checkFeedback("Not Found");
    }

    @Test
    public void mainActivityTest4() {
        setNameInput("Ying");
        added.perform(click());
        checkFeedback("Please Enter user info");
    }

    @Test
    public void mainActivityTest5() {
        String name = "Ying";
        String age = "20";
        int index = 0;

        clearList();
        for(int i=0; i<index; i++) { addRandomToList(i); }

        setNameInput(name);
        setAgeInput(age);
        added.perform(click());
        goToList.perform(click());
        checkList(index, name, age);
    }

    @Test
    public void mainActivityTest6() {
        String name = "Ladarat";
        String age = "20";
        int index = 1;

        clearList();
        for(int i=0; i<index; i++) { addRandomToList(i); }

        setNameInput(name);
        setAgeInput(age);
        added.perform(click());
        goToList.perform(click());
        checkList(index, name, age);
    }

    @Test
    public void mainActivityTest7() {
        String name = "Somkait";
        String age = "80";
        int index = 2;

        clearList();
        for(int i=0; i<index; i++) { addRandomToList(i); }

        setNameInput(name);
        setAgeInput(age);
        added.perform(click());
        goToList.perform(click());
        checkList(index, name, age);
    }

    @Test
    public void mainActivityTest8() {
        String name = "Prayoch";
        String age = "60";
        int index = 3;

        clearList();
        for(int i=0; i<index; i++) { addRandomToList(i); }

        setNameInput(name);
        setAgeInput(age);
        added.perform(click());
        goToList.perform(click());
        checkList(index, name, age);
    }

    @Test
    public void mainActivityTest9() {
        String name = "Prayoch";
        String age = "50";
        int index = 4;

        clearList();
        for(int i=0; i<index; i++) { addRandomToList(i); }

        setNameInput(name);
        setAgeInput(age);
        added.perform(click());
        goToList.perform(click());
        checkList(index, name, age);
    }

    private void setNameInput(String name) {
        nameInput.perform(replaceText(name), closeSoftKeyboard());
    }

    private void setAgeInput(String age) {
        ageInput.perform(replaceText(age), closeSoftKeyboard());
    }

    private void clearList() {
        goToList.perform(click());
        clearList.perform(click());
        pressBack();
    }

    private void addRandomToList(int index) {
        nameInput.perform(replaceText("Temp"), closeSoftKeyboard());
        ageInput.perform(replaceText(String.valueOf(index)), closeSoftKeyboard());
        added.perform(click());
    }

    private void checkFeedback(String keyWord) {
        onView(withText(keyWord)).check(matches(isDisplayed()));
    }

    private void checkList(int position, String name, String age) {
        onView(withRecyclerView(R.id.list).atPositionOnView(position, R.id.textName)).check(matches(withText(name)));
        onView(withRecyclerView(R.id.list).atPositionOnView(position, R.id.textAge)).check(matches(withText(age)));
    }

}
