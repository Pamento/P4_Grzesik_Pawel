package com.pamento.mareu.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.pamento.mareu.ListMeetingActivity;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "______Date____Dialog___";

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
        //TODO add Date.format !
        view.cancelLongPress();
        Object tag = getTag();
        int action = 0;

        // TODO maybe it is not necessery. Wy need to send data to AddNewMeetingDialog and not to ListMeetingActivity
        Log.d(TAG, "onDateSet: TAG " + tag.toString());
        if (tag.equals("newMeeting")) action = 1;
        mListMeetingActivity.checkDateForNextAction(action, dayOfMonth + "/" + (month + 1) + "/" + year);

        sendResult(dayOfMonth + "/" + (month + 1) + "/" + year);
    }

    /**
     * Transfer/sand date from here to AddNewMeetingDialog
     * For display them in TextView-AddMeetingDate
     * @param date
     */
    private void sendResult(String date) {
        if( getTargetFragment() == null ) {
            return;
        }
        Intent intent = AddNewMeetingDialog.newIntent(date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }
}
