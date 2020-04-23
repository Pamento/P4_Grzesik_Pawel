package com.pamento.mareu.utils;

import android.os.Build;

import com.pamento.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FilterMeeting {

    public static List<Meeting> byValue(List<Meeting> meetings, String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return meetings.stream()
                    .filter(Meeting -> value.equals(
                            value.contains("/") ? Meeting.getDate() : Meeting.getHall())).collect(Collectors.toList());
        } else {
            List<Meeting> result = new ArrayList<>();
            int i, s = meetings.size();
            for (i = 0; i < s; i++) {
                if (value.contains("/")) {
                    if (meetings.get(i).getDate().equals(value)) {
                        result.add(meetings.get(i));
                    }
                } else {
                    if (meetings.get(i).getHall().equals(value)) {
                        result.add(meetings.get(i));
                    }
                }
            }
            return result;
        }
    }
}
