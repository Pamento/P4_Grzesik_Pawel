package com.pamento.mareu.utils;

import android.os.Build;

import com.pamento.mareu.model.Meeting;

import java.util.List;
import java.util.stream.Collectors;

public abstract class FilterMeetingBy {
    private static List<Meeting> result;

    public static List<Meeting> byValue(List<Meeting> meetings,String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = meetings.stream()
                    .filter(Meeting -> value.equals(
                            value.contains("/") ? Meeting.getDate() : Meeting.getHall())).collect(Collectors.toList());
        } else {

            for (Meeting meeting : meetings) {
                if (meeting.getDate().equals(value)) {
                    result.add(meeting);
                }
            }
        }
        return result;
    }
}
