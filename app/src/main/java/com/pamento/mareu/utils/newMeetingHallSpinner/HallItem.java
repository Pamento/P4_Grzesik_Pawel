package com.pamento.mareu.utils.newMeetingHallSpinner;

public class HallItem {
    private int mHallImage;
    private String mHallName;

    public HallItem(String hallName, int hallImage) {
        mHallName = hallName;
        mHallImage = hallImage;
    }

    public String getHallName() {
        return mHallName;
    }

    int getHallImage() {
        return mHallImage;
    }
}
