package com.pamento.mareu.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

import com.pamento.mareu.ListMeetingActivity;

import java.util.Calendar;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private ListMeetingActivity mListMeetingActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListMeetingActivity = (ListMeetingActivity) getActivity();
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(Objects.requireNonNull(getActivity()), this, year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        view.cancelLongPress();
        month++;
        String toFrenchMoth = month > 9 ? ""+month : "0"+month;
        Object tag = getTag();
        if (tag != null) {
            if (tag.equals("newMeeting"))
                sendResult(dayOfMonth + "/" + toFrenchMoth + "/" + year);
            else
                mListMeetingActivity.initList(1, dayOfMonth + "/" + toFrenchMoth + "/" + year);
        }
    }

    /**
     * Transfer/sand date from here to AddNewMeetingDialog
     * For display them in TextView-AddMeetingDate
     *
     * @param date to send to AddNewMeetingDialog
     */
    private void sendResult(String date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = AddNewMeetingDialog.newIntent(date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }
}
