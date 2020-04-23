package com.pamento.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pamento.mareu.R;
import com.pamento.mareu.di.DI;
import com.pamento.mareu.events.DeleteMeetingEvent;
import com.pamento.mareu.events.RefreshRecyclerView;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.utils.Constants;
import com.pamento.mareu.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_meetings_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.activity_main_toolbar) Toolbar mToolbar;
    @BindView(R.id.wholeView) ConstraintLayout mPageView;
    private List<Meeting> mMeetings;
    private ApiService mApiService;
    private ListMeetingActivity mThisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.ic_filter_list_black_24dp));
        mApiService = DI.getApiService();
        mThisActivity = this;
        configureRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    /**
     * Function intermediary between menu of main page (filters list the of meetings)
     * and RecyclerView of meetings
     *
     * @param item is the clicked field in menu
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (Tools.switchMenuActions(item.getItemId()) >= 0) {
            initList(Tools.switchMenuActions(item.getItemId()), Tools.hallName(item.getTitle().toString()));
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
    /**
     * Basic call for instantiate the RecyclerView
     */
    public void configureRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        initList(0, null);
    }
    /**
     * Init and refresh RecyclerView ich time the change has been detected
     *
     * @param filterId meetings by: 0 - all meetings   1 - day    2-11 - hall
     */
    public void initList(int filterId, String filterValue) {
        if (filterId == 0) mMeetings = mApiService.getMeetings();
        else if (filterId == 1) {
            if (filterValue != null) {
                mMeetings = mApiService.getMeetingsForOneDay(filterValue);
                if (mMeetings.size() == 0) {
                    Tools.showSnackBar(0, mPageView, Constants.NO_MEETING_AT_DATE + filterValue);
                    mMeetings = mApiService.getMeetings();
                }
            }
        } else if (filterId == -1) {
            Tools.showSnackBar(0, mPageView, Constants.ERROR_MESSAGE);
        } else {
            if (filterValue != null) {
                mMeetings = mApiService.getMeetingsForOneHall(filterValue);
                if (mMeetings.size() == 0) {
                    Tools.showSnackBar(0, mPageView, Constants.NO_MEETING_IN_HALL + Tools.hallName(filterValue) + ".");
                    mMeetings = mApiService.getMeetings();
                }
            }
        }
        mRecyclerView.setAdapter(new MeetingsRecyclerViewAdapter(mMeetings, mThisActivity));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApiService.resetMeetings();
        initList(0, null);
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

    /**
     * OnClick Events: delete meeting from list; refresh list after action; add new Meeting.
     *
     * @param event in @Subscribe tu EventBus whom pass the data
     */
    @Subscribe
    public void OnDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.mMeeting);
        initList(0, null);
    }

    @Subscribe
    public void OnRefreshRecyclerView(RefreshRecyclerView event) {
        initList(event.mActionId, null);
    }

    @OnClick(R.id.activity_list_meeting_fab)
    void addMeeting() {
        AddNewMeetingDialog addMeeting = AddNewMeetingDialog.newInstance();
        addMeeting.show(getSupportFragmentManager(), Constants.NEW_MEETING);
    }

    /**
     * Display DatePickerDialog for filter the meeting RecyclerView
     *
     * @param item of main menu
     */
    public void showDatePickerDialog(MenuItem item) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), Constants.FILTER);
    }
}
