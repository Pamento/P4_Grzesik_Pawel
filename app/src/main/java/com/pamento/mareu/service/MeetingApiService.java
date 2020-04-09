package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Get all meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Function to delete meeting from list
     * @param meeting pass the meeting to delete
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Function to create a new meeting
     * @param meeting new object according to model: Meeting
     */
    void createMeeting(Meeting meeting);
}
