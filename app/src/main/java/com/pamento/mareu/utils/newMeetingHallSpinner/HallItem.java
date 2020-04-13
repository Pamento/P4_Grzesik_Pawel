package com.pamento.mareu.utils.newMeetingHallSpinner;

public class HallItem {
    private String mHallName;
    private int mHallImage;

    public HallItem(String hallName, int hallImage) {
        mHallName = hallName;
        mHallImage = hallImage;
    }

    public String getHallName() {
        return mHallName;
    }

    public int getHallImage() {
        return mHallImage;
    }
}
