package com.pamento.mareu.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.pamento.mareu.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @param actionID 1 for warning; 0 for info message
     * @param view     for which snackbar must by applied
     * @param message  String of content of message.
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

    public static boolean isEmailValid(String email) {
        String regExp =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
