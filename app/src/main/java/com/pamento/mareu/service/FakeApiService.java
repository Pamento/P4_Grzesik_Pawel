package com.pamento.mareu.service;

import android.os.Build;

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
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) { mMeetings.add(meeting); }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetingsForOneDay(String date) {
        mMeetingsByDate = mMeetings.stream()
                .filter(Meeting -> date.equals(Meeting.getDate())).collect(Collectors.toList());
        return mMeetingsByDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetingsForOneHall(String hallName) {
        if (mMeetingsByDate != null) {
            return mMeetingsByDate.stream().filter(Meeting -> hallName.equals(Meeting.getHall())).collect(Collectors.toList());
        } else {
            mMeetingsByHall = mMeetings.stream().filter(Meeting -> hallName.equals(Meeting.getHall())).collect(Collectors.toList());
        }
        return mMeetingsByHall;
    }
}
