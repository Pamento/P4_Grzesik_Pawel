package com.pamento.mareu;

import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.ui.ListMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.pamento.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;

public class DialogFragmentAddMeetingTest {

    private int ITEMS_COUNT;

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
    public void meetingsList_onClick_floatingActionButton_shouldOpen_dialogFragment() {
        onView(withId(R.id.activity_list_meeting_fab)).perform(ViewActions.click());
        onView(withId(R.id.coordinatorLayout)).check(matches(isDisplayed()));
        // Check if click on 'Cancel-dialog' button the dialogFragment gone.
        onView(withId(R.id.meeting_add_cancel_btn)).perform(ViewActions.click()).check(doesNotExist());
    }

    @Test
    public void dialogFragment_fillForm_andSubmit_shouldAddMeetingToRecyclerView() {
        // Open DialogFragment and:
        onView(withId(R.id.activity_list_meeting_fab)).perform(ViewActions.click());
        // 1. Set title
        onView(withId(R.id.meeting_add_title)).perform(ViewActions.typeText("Test Title"));
        // 2. Set date and check his existence
        //onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,4,21));
        onView(withId(R.id.meeting_add_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020, 4, 21));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.meeting_add_date)).check(matches(withText("21/04/2020")));

        // 3. Set hours start
        onView(withId(R.id.meeting_add_hour_start)).perform(ViewActions.click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(1).perform(ViewActions.click());
        // 4. Set hours end
        onView(withId(R.id.meeting_add_hour_end)).perform(ViewActions.click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(2).perform(ViewActions.click());
        // 5. Set hall
        onView(withId(R.id.meeting_add_hall)).perform(ViewActions.click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(1).perform(ViewActions.click());
        // 6. Set 2 participants
        onView(withId(R.id.coordinatorLayout)).perform(ViewActions.swipeUp());
        for (int i = 1; i < 3; i++) {
            String strToPut = "participant_" + i + "@lamzone.com";
            onView(withId(R.id.meeting_add_participants_edit)).perform(ViewActions.typeText(strToPut));
            onView(withId(R.id.meeting_participants_btn)).perform(ViewActions.click(), closeSoftKeyboard());
            onView(withText(endsWith(strToPut))).check(matches(isDisplayed()));
        }
        // Submit form
        onView(withId(R.id.meeting_add_create_btn)).perform(ViewActions.click());
        // Then go back to RecyclerView and his count should be one on more.
        onView(allOf(withId(R.id.activity_main_meetings_recyclerView), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT + 1));

        // Check if filter by hall work for new add of meeting
        // Perform click on menu
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        // From menu select option filter by hall
        onView(withText("Dans une salle")).perform(ViewActions.click());
        // chose the day by date
        onView(withText("Salle A")).perform(ViewActions.click());
        // RecyclerView should display 2 row/Meetings.
        onView(allOf(withId(R.id.activity_main_meetings_recyclerView), isDisplayed()))
                .check(withItemCount(2));
    }
}
