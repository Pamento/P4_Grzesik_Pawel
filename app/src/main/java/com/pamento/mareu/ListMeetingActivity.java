package com.pamento.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.events.DeleteMeetingEvent;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.ui.AddNewMeetingDialog;
import com.pamento.mareu.ui.DatePickerFragment;
import com.pamento.mareu.utils.MeetingsRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {
    private static final String TAG = "______DiALOG_______";

    @BindView(R.id.activity_main_meetings_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.activity_main_toolbar) Toolbar mToolbar;
    private List<Meeting> mMeetings;
    private ApiService mApiService;
    private ListMeetingActivity mThisActivity;
    boolean isLargeLayout;
    // Current result of DataPickerDialog
//    public static String mDate;
//
//    public static String getDate() {
//        return mDate;
//    }
//
//    public static void setDate(String mDate) {
//        Log.d(TAG, "day FILTER _day_is: "+mDate);
//        ListMeetingActivity.mDate = mDate;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mApiService = DI.getApiService();
        mThisActivity = this;
        isLargeLayout = getResources().getBoolean(R.bool.large_layout);
        configureRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * To manage change in the menu
     * https://developer.android.com/reference/android/app/Activity#onPrepareOptionsMenu(android.view.Menu)
     *
     * @param menu existing menu content
     * @return boolean
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
        // TODO for manage the actions inside menu after is created
    }

    public void checkDateForNextAction(int action, String date) {
        if (action == 0) {
            Log.d(TAG, "_________Next_0 filter__Action: "+date);
        } else {
            Log.d(TAG, "_________Next_ 1 newMeeting__Action: "+date);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_post_all:
                initList(0);
                Toast.makeText(this, "Filter Reset", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_by_day:
                Toast.makeText(this, "Filter by Day", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_by_hall:
                Toast.makeText(this, "Filter by Hall", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Basic call for instantiate the RecyclerView
     */
    public void configureRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        initList(0);
    }

    /**
     * Init and refresh RecyclerView ich time the change has been detected
     * @param filter meetings by: 0 - all meetings   1 - day    2 - hall
     */
    public void initList(int filter) {
        switch (filter) {
            case 0:
                mMeetings = mApiService.getMeetings();
                break;
            case 1:
                // TODO add day filter
                // mMeetings = mApiService.getForOneDayMeetings();
                break;
            case 2:
                // TODO add hall filter
                // mMeetings = mApiService.getForOneHallMeetings();
                break;
            default:
                System.out.println("Error: Unknown Filter!");
                break;
        }
        mRecyclerView.setAdapter(new MeetingsRecyclerViewAdapter(mMeetings, mThisActivity));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO check how this works with filters
        initList(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void OnDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        configureRecyclerView();
    }

    @OnClick(R.id.activity_list_meeting_fab)
    void addMeeting() {
//        AddNewMeetingDialog dialogForm = new AddNewMeetingDialog();
//        dialogForm.show(getSupportFragmentManager(), "AddNewMeetingDialog");
        AddNewMeetingDialog addMeeting = AddNewMeetingDialog.newInstance("Add meeting");
        addMeeting.show(getSupportFragmentManager(),"newMeeting");
    }

    public void showDatePickerDialog(MenuItem item) {
        Log.d(TAG, "filter ");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "filter");
    }
}
