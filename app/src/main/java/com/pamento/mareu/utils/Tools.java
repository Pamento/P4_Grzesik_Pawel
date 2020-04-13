package com.pamento.mareu.utils;

import android.util.Log;

import com.pamento.mareu.R;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallItem;

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
        hallItems.add(new HallItem("une Salle", R.drawable.hall_0));
        hallItems.add(new HallItem("une hall_a", R.drawable.hall_a));
        hallItems.add(new HallItem("une hall_b", R.drawable.hall_b));
        hallItems.add(new HallItem("une hall_c", R.drawable.hall_c));
        hallItems.add(new HallItem("une hall_d", R.drawable.hall_d));
        hallItems.add(new HallItem("une hall_e", R.drawable.hall_e));
        hallItems.add(new HallItem("une hall_f", R.drawable.hall_f));
        hallItems.add(new HallItem("une hall_g", R.drawable.hall_g));
        hallItems.add(new HallItem("une hall_h", R.drawable.hall_h));
        hallItems.add(new HallItem("une hall_i", R.drawable.hall_i));
        hallItems.add(new HallItem("une hall_j", R.drawable.hall_j));

        return hallItems;
    }
}
