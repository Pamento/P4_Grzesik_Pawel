package com.pamento.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.service.FakeApiService;
import com.pamento.mareu.utils.MeetingsRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_meetings_recyclerView)
    RecyclerView mRecyclerView;
    private List<Meeting> mMeetings;
    private MeetingsRecyclerViewAdapter mAdapter;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DI.getApiService();
        mMeetings = mApiService.getMeetings();
    }

    public void configureRecyclerView() {
        mAdapter = new MeetingsRecyclerViewAdapter(mMeetings,this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
