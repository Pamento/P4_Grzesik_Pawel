package com.pamento.mareu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.events.DeleteMeetingEvent;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.ui.AddNewMeetingDialog;
import com.pamento.mareu.utils.MeetingsRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_meetings_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;
    private List<Meeting> mMeetings;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mApiService = DI.getApiService();
        mMeetings = mApiService.getMeetings();

        configureRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.filter_by_day:
                Toast.makeText(this, "Filter by Day", Toast.LENGTH_SHORT).show();
                break;
            case R.id.filter_by_hall:
                Toast.makeText(this, "Filter by Hall", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void configureRecyclerView() {
        MeetingsRecyclerViewAdapter adapter = new MeetingsRecyclerViewAdapter(mMeetings, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureRecyclerView();
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
        AddNewMeetingDialog dialogForm = new AddNewMeetingDialog();
        dialogForm.show(getSupportFragmentManager(), "AddNewMeetingDialog");
    }
}
