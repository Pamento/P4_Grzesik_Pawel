package com.pamento.mareu;

import android.content.Context;
import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.pamento.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MeetingsListTest {

    private int ITEMS_COUNT;

    /**
     * The {@link androidx.recyclerview.widget.RecyclerView} resource id.
     */
    private final int recyclerViewID = R.id.activity_main_meetings_recyclerView;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule = new ActivityTestRule<>(ListMeetingActivity.class);

    @Before
    public void setup() {
        ListMeetingActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
        ApiService apiService = DI.getNewInstanceApiService();
        ITEMS_COUNT = apiService.getMeetings().size();
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pamento.mareu", appContext.getPackageName());
    }
    @Test
    public void meetingsList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(this.recyclerViewID), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingsList_deleteAction_shouldRemoveOneRow() {
        // Given : remove the element at position 2
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void meetingList_onClick_menu_dataPicker_shouldOpen_datePicker() {
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        onView(withText("Dans une journée")).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).check(matches(isDisplayed()));
        // button1 - OK, button2 - Cancel
        onView(withId(android.R.id.button2)).perform(ViewActions.click())
                .check(doesNotExist());
        //onView(withId(16908812)).check(matches(isDisplayed()));
    }

    @Test
    public void meetingList_selectDate_onFilter_should_displayMeetingsFromOneDay() {
        // Perform click on menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        // From menu select second option for filter by one day
        onView(withText("Dans une journée")).perform(ViewActions.click());
        // chose the day by date
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020, 5, 13));
        // Submit the date choice
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        // RecyclerView should display 4 row/Meetings.
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .check(withItemCount(4));
    }

    @Test
    public void meetingList_selectDate_onFilter_should_displayMeetingsFromOneHall() {
        // Perform click on menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        // From menu select option filter by hall
        onView(withText("Dans une salle")).perform(ViewActions.click());
        // chose the day by date
        onView(withText("Salle B")).perform(ViewActions.click());
        // Submit the date choice
        //onView(withId(android.R.id.button1)).perform(ViewActions.click());
        // RecyclerView should display 3 row/Meetings.
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .check(withItemCount(3));
        // Reset filter
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        onView(withText("Toutes les réunions")).perform(ViewActions.click());
        // RecyclerView should display 16/ITEM_COUNT row/Meetings.
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));
    }
}
