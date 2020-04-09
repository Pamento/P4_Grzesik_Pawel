package com.pamento.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.service.FakeApiService;
import com.pamento.mareu.utils.MeetingsRecyclerViewAdapter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_meetings_recyclerView)
    RecyclerView mRecyclerView;
    private List<Meeting> mMeetings;
    private MeetingsRecyclerViewAdapter mAdapter;
    private ApiService mApiService;
    private static final String TAG = "RecyclerView Act";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApiService = DI.getApiService();
        mMeetings = mApiService.getMeetings();

        configureRecyclerView();
        Log.d(TAG, "onCreate: mMeetings "+mMeetings.size());
    }

    public void configureRecyclerView() {
        mAdapter = new MeetingsRecyclerViewAdapter(mMeetings,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onCreate: adapter "+mAdapter.toString());
    }
}
