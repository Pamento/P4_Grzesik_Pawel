package com.pamento.mareu.utils;

import android.util.Log;

import com.pamento.mareu.R;

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
}
