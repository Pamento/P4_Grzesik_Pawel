package com.pamento.mareu.events;

import com.pamento.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    /**
     * Constructor
     * @param meeting pass single meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        mMeeting = meeting;
    }
}
