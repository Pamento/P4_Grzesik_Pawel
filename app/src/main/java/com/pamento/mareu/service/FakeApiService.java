package com.pamento.mareu.service;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.pamento.mareu.model.Meeting;

import java.util.List;
import java.util.stream.Collectors;

import static com.pamento.mareu.service.FakeApiServiceGenerator.generateMeetings;

public class FakeApiService implements ApiService {

    private List<Meeting> mMeetings = generateMeetings();
    private List<Meeting> mMeetingsByDate;
    private List<Meeting> mMeetingsByHall;

    @Override
    public void setMeetings() {
        mMeetings = generateMeetings();
    }

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetingsForOneDay(String date) {
        if (mMeetingsByHall != null) {
            return mMeetingsByHall.stream()
                    .filter(Meeting -> date.equals(Meeting.getDate())).collect(Collectors.toList());
        }
        mMeetingsByDate = mMeetings.stream()
                .filter(Meeting -> date.equals(Meeting.getDate())).collect(Collectors.toList());
        return mMeetingsByDate;
    }

    private static final String TAG = "FakeApiService";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetingsForOneHall(String hallName) {
        Log.d(TAG, "getMeetingsForOneHall: "+hallName);
        if (mMeetingsByDate != null) {
            Log.d(TAG, "___if____getMeetingsForOneHall: "+hallName);
            return mMeetingsByDate.stream().filter(Meeting -> hallName.equals(Meeting.getHall())).collect(Collectors.toList());
        } else {
            Log.d(TAG, "____else____getMeetingsForOneHall: "+hallName);
            mMeetingsByHall = mMeetings.stream().filter(Meeting -> hallName.equals(Meeting.getHall())).collect(Collectors.toList());
        }
        return mMeetingsByHall;
    }
}
