package com.pamento.mareu;

import com.pamento.mareu.di.DI;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.service.Resources;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private ApiService service;
    private String getMeetingsFromThisDate = "13/05/2020";
    private String getGetMeetingsInThisHall = "hall_a";

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetings_success() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = Resources.MOCKS_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedMeetings.toArray())));
    }

    @Test
    public void deleteMeeting_success() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeeting_success() {
        List<String> participants = Arrays.asList("one@participant.com", "one@participant.com");
        Meeting newMeeting = new Meeting(
                System.currentTimeMillis(),
                "Title",
                "30/04/2020",
                "08:00",
                "08:30",
                "hall_a",
                participants);
        service.createMeeting(newMeeting);
        assertTrue(service.getMeetings().contains(newMeeting));
    }

    @Test
    public void getMeetingByDate_success() {
        List<Meeting> meetingsFromOneDay = service.getMeetingsForOneDay(getMeetingsFromThisDate);
        assertThat(meetingsFromOneDay, IsIterableContainingInAnyOrder.containsInAnyOrder(Resources.MOCKS_MEETINGS.stream()
        .filter(Meeting->getMeetingsFromThisDate.equals(Meeting.getDate())).toArray()));
    }

    @Test
    public void getMeetingsByHall_success() {
        List<Meeting> meetingsInOneHall = service.getMeetingsForOneHall(getGetMeetingsInThisHall);
        assertThat(meetingsInOneHall, IsIterableContainingInAnyOrder.containsInAnyOrder(Resources.MOCKS_MEETINGS.stream()
        .filter(Meeting->getGetMeetingsInThisHall.equals(Meeting.getHall())).toArray()));
    }
}