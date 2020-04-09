package com.pamento.mareu.model;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class Meeting {

    /** Identifier */
    private long id;

    /** Title */
    private String title;

    /** Date */
    private String date;

    /** Hall */
    private String hall;

    /** List of participants */
    private List<String> participants;

    public Meeting(long id, String title, String date, String hall, List<String> participants) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.hall = hall;
        this.participants = participants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
