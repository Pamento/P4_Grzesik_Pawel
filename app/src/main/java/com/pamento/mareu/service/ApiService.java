package com.pamento.mareu.service;

import com.pamento.mareu.model.Meeting;

import java.util.List;

public interface ApiService {

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

    /**
     * Filter for meeting by day chosen by user
     * @param date of the day.
     * @return
     */
    List<Meeting> getMeetingsForOneDay(String date);

    /**
     * Filter for meeting by hall chosen by user
     * @param hallName name of hall.
     */
    List<Meeting> getMeetingsForOneHall(String hallName);
}
