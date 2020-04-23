package com.pamento.mareu.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.utils.FilterMeetingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetingsForOneDay(String date) {
        if (mMeetingsByHall != null) {
            mMeetingsByHall = FilterMeetingBy.byValue(mMeetingsByHall,date);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                return mMeetingsByHall.stream()
//                        .filter(Meeting -> date.equals(Meeting.getDate())).collect(Collectors.toList());
//            } else {
//                List<Meeting> result = new ArrayList<>();
//                for (Meeting meeting : mMeetingsByHall) {
//                    if (meeting.getDate().equals(date)) {
//                        result.add(meeting);
//                    }
//                }
//                mMeetingsByHall = result;
//            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mMeetingsByDate = mMeetings.stream()
                        .filter(Meeting -> date.equals(Meeting.getDate())).collect(Collectors.toList());
            }

        }
        //else {
            List<Meeting> result = new ArrayList<>();
            for (Meeting meeting : mMeetings) {
                if (meeting.getDate().equals(date)) {
                    result.add(meeting);
                }
            }
            mMeetingsByDate = result;

        //}
        return mMeetingsByDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetingsForOneHall(String hallName) {
        if (mMeetingsByDate != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return mMeetingsByDate.stream().filter(Meeting -> hallName.equals(Meeting.getHall())).collect(Collectors.toList());

            } else {

                List<Meeting> result = new ArrayList<>();
                for (Meeting meeting : mMeetingsByDate) {
                    if (meeting.getHall().equals(hallName)) {
                        result.add(meeting);
                    }
                }
                mMeetingsByDate = result;
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mMeetingsByHall = mMeetings.stream().filter(Meeting -> hallName.equals(Meeting.getHall())).collect(Collectors.toList());

            } else {

                List<Meeting> result = new ArrayList<>();
                for (Meeting meeting : mMeetings) {
                    if (meeting.getHall().equals(hallName)) {
                        result.add(meeting);
                    }
                }
                mMeetingsByHall = result;
            }
        }
        return mMeetingsByHall;
    }
}
