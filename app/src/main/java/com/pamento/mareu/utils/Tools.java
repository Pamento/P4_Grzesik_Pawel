package com.pamento.mareu.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.pamento.mareu.R;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallItem;
import com.pamento.mareu.utils.newMeetingHourSpinner.Hour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Tools {

    public static int switchMenuActions(int actionId) {
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
        return filterId;
    }

    public static List<HallItem> initHallSpinnerList() { return new ArrayList<>(HALL_LIST); }

    private static List<HallItem> HALL_LIST = Arrays.asList(
            new HallItem("hall_0", R.drawable.hall_0),
            new HallItem("hall_a", R.drawable.hall_a),
            new HallItem("hall_b", R.drawable.hall_b),
            new HallItem("hall_c", R.drawable.hall_c),
            new HallItem("hall_d", R.drawable.hall_d),
            new HallItem("hall_e", R.drawable.hall_e),
            new HallItem("hall_f", R.drawable.hall_f),
            new HallItem("hall_g", R.drawable.hall_g),
            new HallItem("hall_h", R.drawable.hall_h),
            new HallItem("hall_i", R.drawable.hall_i),
            new HallItem("hall_j", R.drawable.hall_j)
    );

    public static List<Hour> initHourSpinnerList() {
        return new ArrayList<>(HOUR_LIST);
    }

    private static List<Hour> HOUR_LIST = Arrays.asList(
            new Hour("l'heur"),
            new Hour("08:00"),
            new Hour("08:15"),
            new Hour("08:30"),
            new Hour("08:45"),
            new Hour("09:00"),
            new Hour("09:15"),
            new Hour("09:30"),
            new Hour("09:45"),
            new Hour("10:00"),
            new Hour("10:15"),
            new Hour("10:30"),
            new Hour("10:45"),
            new Hour("11:00"),
            new Hour("11:15"),
            new Hour("11:30"),
            new Hour("11:45"),
            new Hour("12:00"),
            new Hour("12:15"),
            new Hour("12:30"),
            new Hour("12:45"),
            new Hour("13:00"),
            new Hour("13:15"),
            new Hour("13:30"),
            new Hour("13:45"),
            new Hour("14:00"),
            new Hour("14:15"),
            new Hour("14:30"),
            new Hour("14:45"),
            new Hour("15:00"),
            new Hour("15:15"),
            new Hour("15:30"),
            new Hour("15:45"),
            new Hour("16:00"),
            new Hour("16:15"),
            new Hour("16:30"),
            new Hour("16:45"),
            new Hour("17:00")
    );

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
                name = "Salle J";
                break;
            case "Salle A":
                name = "hall_a";
                break;
            case "Salle B":
                name = "hall_b";
                break;
            case "Salle C":
                name = "hall_c";
                break;
            case "Salle D":
                name = "hall_d";
                break;
            case "Salle E":
                name = "hall_e";
                break;
            case "Salle F":
                name = "hall_f";
                break;
            case "Salle G":
                name = "hall_g";
                break;
            case "Salle H":
                name = "hall_h";
                break;
            case "Salle I":
                name = "hall_i";
                break;
            case "Salle J":
                name = "hall_j";
                break;
            default:
                name = "error";
        }
        return name;
    }

    /**
     * @param actionID          1 for warning; 0 for info message
     * @param view for which snackbar must by applied
     * @param message String of content of message.
     */
    public static void showSnackBar(int actionID, View view, String message) {

        if (actionID == 1) {

            Snackbar snackbar = Snackbar
                    .make(view, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snack_action_btn, v -> {
                        Snackbar snackBarError = Snackbar.make(view, R.string.thanks, Snackbar.LENGTH_SHORT);
                        snackBarError.show();
                    })
                    .setActionTextColor(Color.WHITE);

            View snackView = snackbar.getView();
            TextView textView = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);

            snackbar.show();

        } else {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
