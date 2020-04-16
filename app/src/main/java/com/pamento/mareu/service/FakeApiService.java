package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.List;

import static com.pamento.mareu.service.FakeApiServiceGenerator.generateMeetings;

public class FakeApiService implements ApiService {

    private List<Meeting> mMeetings = generateMeetings();

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
}
