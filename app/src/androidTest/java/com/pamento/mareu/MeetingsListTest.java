package com.pamento.mareu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.DatePicker;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.chip.Chip;
import com.pamento.mareu.di.DI;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasLinks;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.pamento.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingsListTest {

    private int ITEMS_COUNT;

    /**
     * The {@link androidx.recyclerview.widget.RecyclerView} resource id.
     */
    private final int recyclerViewID = R.id.activity_main_meetings_recyclerView;
    private List<Meeting> mMeetings;

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
    public void meetingsList_onClick_floatingActionButton_shouldOpen_dialogFragment() {
        onView(withId(R.id.activity_list_meeting_fab)).perform(ViewActions.click());
        onView(withId(R.id.coordinatorLayout)).check(matches(isDisplayed()));
        // Check if click on 'Cancel-dialog' button the dialogFragment gone.
        onView(withId(R.id.meeting_add_cancel_btn)).perform(ViewActions.click()).check(doesNotExist());
    }

    @Test
    public void dialogFragment_setAllFields_shouldAddMeetingToRecyclerView() {
        // Open DialogFragment and:
        onView(withId(R.id.activity_list_meeting_fab)).perform(ViewActions.click());
        // 1. Set title
        onView(withId(R.id.meeting_add_title)).perform(ViewActions.typeText("Test Title"));
        // 2. Set date and check his existence
        //onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,4,21));
        onView(withId(R.id.meeting_add_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,4,21));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.meeting_add_date)).check(matches(withText("21/4/2020")));

        // 3. Set hours start
        onView(withId(R.id.meeting_add_hour_start)).perform(ViewActions.click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(1).perform(ViewActions.click());
        // 4. Set hours end
        onView(withId(R.id.meeting_add_hour_end)).perform(ViewActions.click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(2).perform(ViewActions.click());
        // 5. Set hall
        onView(withId(R.id.meeting_add_hall)).perform(ViewActions.click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(1).perform(ViewActions.click());
//        try {
//            Thread.sleep(500);
//            //onView(isRoot()).wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //onView(withId(R.id.meeting_add_hour_start)).check(matches(withSpinnerText(containsString("08:00"))));

        // 6. Set participants
        onView(withId(R.id.coordinatorLayout)).perform(ViewActions.swipeUp());
        for (int i = 1; i<3; i++) {
            String strToPut = "participant_"+i+"@lamzone.com";
            onView(withId(R.id.meeting_add_participants_edit)).perform(ViewActions.typeText(strToPut));
            onView(withId(R.id.meeting_participants_btn)).perform(ViewActions.click(),closeSoftKeyboard());
            //onView(withId(R.id.coordinatorLayout)).perform(ViewActions.swipeUp());
            //onView(withId(R.id.meeting_list_participants)).check(matches(isAssignableFrom(Chip.class),withText("participant_"+i+"@lamzone.com")));

            onView(withText(endsWith(strToPut))).check(matches(isDisplayed()));
        }

        onView(withId(R.id.meeting_add_create_btn)).perform(ViewActions.click());
        // Then go back to RecyclerView and his count should be one on more.
        onView(allOf(withId(recyclerViewID), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT+1));

    }
}
