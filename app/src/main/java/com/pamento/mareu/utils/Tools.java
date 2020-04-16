package com.pamento.mareu.utils;

import android.util.Log;

import com.pamento.mareu.R;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallItem;
import com.pamento.mareu.utils.newMeetingHourSpinner.Hour;

import java.util.ArrayList;

public abstract class Tools {
    private static final String TAG = "Tools";

    public static int switchMenuActions(int actionId) {
        Log.d(TAG, "switchMenuActions: " + actionId);
        int filterId;
        switch (actionId) {
            case R.id.filter_post_all:
                filterId = 0;
                break;
            case R.id.filter_by_day:
                filterId = 1;
                break;
            case R.id.filter_h1:
                filterId = 2;
                break;
            case R.id.filter_h2:
                filterId = 3;
                break;
            case R.id.filter_h3:
                filterId = 4;
                break;
            case R.id.filter_h4:
                filterId = 5;
                break;
            case R.id.filter_h5:
                filterId = 6;
                break;
            case R.id.filter_h6:
                filterId = 7;
                break;
            case R.id.filter_h7:
                filterId = 8;
                break;
            case R.id.filter_h8:
                filterId = 9;
                break;
            case R.id.filter_h9:
                filterId = 10;
                break;
            case R.id.filter_h10:
                filterId = 11;
                break;
            default:
                filterId = -1;
                break;
        }
        Log.d(TAG, "switchMenuActions: filterId " + filterId);
        return filterId;
    }

    public static ArrayList<HallItem> initHallSpinnerList() {
        ArrayList<HallItem> hallItems = new ArrayList<>();
        hallItems.add(new HallItem("hall_0", R.drawable.hall_0));
        hallItems.add(new HallItem("hall_a", R.drawable.hall_a));
        hallItems.add(new HallItem("hall_b", R.drawable.hall_b));
        hallItems.add(new HallItem("hall_c", R.drawable.hall_c));
        hallItems.add(new HallItem("hall_d", R.drawable.hall_d));
        hallItems.add(new HallItem("hall_e", R.drawable.hall_e));
        hallItems.add(new HallItem("hall_f", R.drawable.hall_f));
        hallItems.add(new HallItem("hall_g", R.drawable.hall_g));
        hallItems.add(new HallItem("hall_h", R.drawable.hall_h));
        hallItems.add(new HallItem("hall_i", R.drawable.hall_i));
        hallItems.add(new HallItem("hall_j", R.drawable.hall_j));

        return hallItems;
    }

    public static ArrayList<Hour> initHourSpinnerList() {
        ArrayList<Hour> hours = new ArrayList<>();
        hours.add(new Hour("08:00"));
        hours.add(new Hour("08:15"));
        hours.add(new Hour("08:30"));
        hours.add(new Hour("08:45"));
        hours.add(new Hour("09:00"));
        hours.add(new Hour("09:15"));
        hours.add(new Hour("09:30"));
        hours.add(new Hour("09:45"));
        hours.add(new Hour("10:00"));
        hours.add(new Hour("10:15"));
        hours.add(new Hour("10:30"));
        hours.add(new Hour("10:45"));
        hours.add(new Hour("11:00"));
        hours.add(new Hour("11:15"));
        hours.add(new Hour("11:30"));
        hours.add(new Hour("11:45"));
        hours.add(new Hour("12:00"));
        hours.add(new Hour("12:15"));
        hours.add(new Hour("12:30"));
        hours.add(new Hour("12:45"));
        hours.add(new Hour("13:00"));
        hours.add(new Hour("13:15"));
        hours.add(new Hour("13:30"));
        hours.add(new Hour("13:45"));
        hours.add(new Hour("14:00"));
        hours.add(new Hour("14:15"));
        hours.add(new Hour("14:30"));
        hours.add(new Hour("14:45"));
        hours.add(new Hour("15:00"));
        hours.add(new Hour("15:15"));
        hours.add(new Hour("15:30"));
        hours.add(new Hour("15:45"));
        hours.add(new Hour("16:00"));
        hours.add(new Hour("16:15"));
        hours.add(new Hour("16:30"));
        hours.add(new Hour("16:45"));
        hours.add(new Hour("17:00"));
        hours.add(new Hour("17:15"));
        hours.add(new Hour("17:30"));
        hours.add(new Hour("17:45"));
        hours.add(new Hour("18:00"));
        hours.add(new Hour("18:15"));
        hours.add(new Hour("18:30"));
        hours.add(new Hour("18:45"));
        hours.add(new Hour("19:00"));
        hours.add(new Hour("19:15"));
        hours.add(new Hour("19:30"));
        hours.add(new Hour("19:45"));
        hours.add(new Hour("20:00"));
        hours.add(new Hour("20:15"));
        hours.add(new Hour("20:30"));
        hours.add(new Hour("20:45"));
        hours.add(new Hour("21:00"));
        hours.add(new Hour("21:15"));
        hours.add(new Hour("21:30"));
        hours.add(new Hour("21:45"));
        hours.add(new Hour("22:00"));

        return hours;
    }

    public static String hallName(String hall) {
        String name;
        switch (hall) {
            case "hall_a":
                name = "Salle A";
                break;
            case "hall_b":
                name = "Salle B";
                break;
            case "hall_c":
                name = "Salle C";
                break;
            case "hall_d":
                name = "Salle D";
                break;
            case "hall_e":
                name = "Salle E";
                break;
            case "hall_f":
                name = "Salle F";
                break;
            case "hall_g":
                name = "Salle G";
                break;
            case "hall_h":
                name = "Salle H";
                break;
            case "hall_i":
                name = "Salle I";
                break;
            case "hall_j":
                name = "Salle j";
                break;
            default:
                name = "error";
        }
        return name;
    }
}
