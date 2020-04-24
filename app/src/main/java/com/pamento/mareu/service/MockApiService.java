package com.pamento.mareu.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.utils.FilterMeeting;

import java.util.List;

import static com.pamento.mareu.service.Resources.mockingMeetings;

public class MockApiService implements ApiService {

    private List<Meeting> mMeetings = mockingMeetings();
    private List<Meeting> mMeetingsByDate;
    private List<Meeting> mMeetingsByHall;

    @Override
    public void resetMeetings() {
        mMeetings = mockingMeetings();
    }

    @Override
    public List<Meeting> getMeetings() {
        if (mMeetingsByDate != null) {
            mMeetingsByDate.clear();
            mMeetingsByDate = null;
        }
        if (mMeetingsByHall != null) {
            mMeetingsByHall.clear();
            mMeetingsByHall = null;
        }
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

    @Override
    public List<Meeting> getMeetingsForOneDay(String date) {
        if (mMeetingsByHall != null) {
            mMeetingsByDate = FilterMeeting.byValue(mMeetingsByHall, date);
            return mMeetingsByDate;
        }
        else
            mMeetingsByDate = FilterMeeting.byValue(mMeetings, date);
        return mMeetingsByDate;
    }

    @Override
    public List<Meeting> getMeetingsForOneHall(String hallName) {
        if (mMeetingsByDate != null) {
            mMeetingsByHall = FilterMeeting.byValue(mMeetingsByDate, hallName);
            return mMeetingsByHall;
        }
        else
            mMeetingsByHall = FilterMeeting.byValue(mMeetings, hallName);
        return mMeetingsByHall;
    }
}
