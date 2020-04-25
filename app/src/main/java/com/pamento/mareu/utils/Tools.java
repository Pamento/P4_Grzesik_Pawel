package com.pamento.mareu.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.pamento.mareu.R;
import com.pamento.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Tools {

    private static String title;
    private static String date;
    private static String fromHour;
    private static String mToHour;
    private static String mHall;
    private static List<String> mParticipants;

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

    public static boolean editParticipant(View view, String input) {
        if (isEmailValid(input)) {
            if (meeting.getParticipants().contains(input))
                meeting.setParticipants(Collections.singletonList(input));
            return true;
        } else {
            Tools.showSnackBar(1, view, Constants.ERROR_INVALID_EMAIL);
            return false;
        }
    }

    public static Meeting meeting = new Meeting(
            System.currentTimeMillis(),
            title = "",
            date = "",
            fromHour = "",
            mToHour = "",
            mHall = "",
            mParticipants = new ArrayList<>()
    );

    public static boolean isFormValid(View view, String value) {
        if (editParticipant(view, value))
            mParticipants.add(value);

        StringBuilder message = new StringBuilder();
        if (meeting.getTitle().equals("")) {
            message.append(" sujet");
        }
        if (meeting.getDate().equals("")) {
            message.append(message.length() == 0 ? " date" : ", date");
        }
        if (meeting.getHourStart().equals("l'heure")) {
            message.append(message.length() == 0 ? " le début" : ", le début");
        }
        if (meeting.getHourEnd().equals("l'heure")) {
            message.append(message.length() == 0 ? " la fin" : ", la fin");
        }
        if (meeting.getHall().equals("hall_0")) {
            message.append(message.length() == 0 ? " la salle" : ", la salle");
        }
        if (meeting.getParticipants().size() <= 1) {
            message.append(message.length() == 0 ? " les participants" : ", les participants");
        }
        if (!meeting.getHourStart().equals("l'heure") && !meeting.getHourEnd().equals("l'heure") && meeting.getHourStart().equals(meeting.getHourEnd())) {
            Tools.showSnackBar(1, view, Constants.WARNING_SAME_HOURS);
            return false;
        } else if (message.length() != 0) {
            Tools.showSnackBar(1, view, "Il manque: " + message + ".");
            return false;
        } else {
            return true;
        }
    }
}
