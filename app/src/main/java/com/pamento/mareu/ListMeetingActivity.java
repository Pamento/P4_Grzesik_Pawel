package com.pamento.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.events.DeleteMeetingEvent;
import com.pamento.mareu.events.RefreshRecyclerView;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.ui.AddNewMeetingDialog;
import com.pamento.mareu.ui.DatePickerFragment;
import com.pamento.mareu.utils.Constants;
import com.pamento.mareu.utils.MeetingsRecyclerViewAdapter;
import com.pamento.mareu.utils.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {
    private static final String TAG = "______DiALOG_______";

    @BindView(R.id.activity_main_meetings_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.wholeView)
    ConstraintLayout mPageView;
    private List<Meeting> mMeetings;
    private ApiService mApiService;
    private ListMeetingActivity mThisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mApiService = DI.getApiService();
        mThisActivity = this;
        configureRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
//
//    /**
//     * To manage the eventually changes on/in the menu View.
//     * https://developer.android.com/reference/android/app/Activity#onPrepareOptionsMenu(android.view.Menu)
//     *
//     * @param menu existing menu content
//     * @return boolean
//     */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//        // TODO for manage the actions inside menu after is created
//    }

    public void checkDateForNextAction(int action, String date) {
        if (action == 0) {
            Log.d(TAG, "_________Next_0 filter__Action: " + date);
            initList(1, date);
        } else {
            Log.d(TAG, "_________Next_ 1 newMeeting__Action: " + date);
            Toast.makeText(this, "Chosen date is: " + date, Toast.LENGTH_SHORT).show();
        }
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
        Log.d(TAG, "onOptionsItemSelected: ######__fired__###### " + item.getItemId());
        if (Tools.switchMenuActions(item.getItemId()) >= 0) {
            initList(Tools.switchMenuActions(item.getItemId()), Tools.hallName(item.getTitle().toString()));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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
        Log.d(TAG, "initList: filter " + filterId);
        if (filterId == 0) mMeetings = mApiService.getMeetings();
        else if (filterId == 1) {
            if (filterValue != null) {
                mMeetings = mApiService.getMeetingsForOneDay(filterValue);
                Log.d(TAG, "initList: +++++++++++++++++++++++meetings.SIZE( " + mMeetings.size() + " )");
                if (mMeetings.size() == 0) {
                    Tools.showSnackBar(0, mPageView, Constants.NO_MEETING_AT_DATE + filterValue);
                    mMeetings = mApiService.getMeetings();
                }
            }

        } else if (filterId == -1) {
            Tools.showSnackBar(0, mPageView, "Un error est parvenu. Essayez à nouveau.");
        } else {
            if (filterValue != null) {
                mMeetings = mApiService.getMeetingsForOneHall(filterValue);
                Log.d(TAG, "initList: ++++++++++++++++++++++++meetings.SIZE( " + mMeetings.size() + " )");
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
        // TODO check how this works with filters
        // TODO check if here wy need reset mMeetings
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: FIRED");
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
        AddNewMeetingDialog addMeeting = AddNewMeetingDialog.newInstance(Constants.ADD_MEETING);
        addMeeting.show(getSupportFragmentManager(), Constants.NEW_MEETING);
    }

    /**
     * Display DatePickerDialog for filter the meeting RecyclerView
     *
     * @param item of main menu
     */
    public void showDatePickerDialog(MenuItem item) {
        Log.d(TAG, "filter ");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), Constants.FILTER);
    }
}
