package com.pamento.mareu.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.pamento.mareu.ListMeetingActivity;
import com.pamento.mareu.R;
import com.pamento.mareu.utils.Constants;
import com.pamento.mareu.utils.Tools;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallItem;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallSpinnerAdapter;
import com.pamento.mareu.utils.newMeetingHourSpinner.Hour;
import com.pamento.mareu.utils.newMeetingHourSpinner.HourSpinnerAdapter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewMeetingDialog extends DialogFragment {
    private static final String TAG = "____DiALOG___newMeeting";

    @BindView(R.id.meeting_add_cancel_btn)
    ImageButton mCancelDialogBtn;

    private View mView;
    private Context mContext;

    // REQUIRED EMPTY CONSTRUCTOR
    public AddNewMeetingDialog() {
    }
    // AddNewMeetingDialog(DialogFragment) instance with arguments assignment
    public static AddNewMeetingDialog newInstance(String title) {
        AddNewMeetingDialog frag = new AddNewMeetingDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind((ListMeetingActivity) Objects.requireNonNull(getActivity()));
        return inflater.inflate(R.layout.dialog_new_meeting, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        // Get field from view
        EditText editText = (EditText) view.findViewById(R.id.meeting_add_title);
        // Fetch arguments from bundle and set title
        String title = getArguments() != null ? getArguments().getString("title", "Enter Name") : null;
        Objects.requireNonNull(getDialog()).setTitle(title);
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        actionToDo(view);

        mContext = getContext();
        // Hall Spinner =========================
        configureHallPikerSpinner(mContext,view);
        // Hour Spinner =========================
        configureHourPickerSpinner(mContext,view);
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        // Call super onResume after sizing
        super.onResume();
    }

    //@OnClick(R.id.meeting_add_cancel_btn)
    private void actionToDo(View view) {
        // Display DataPicker
        TextView addDate = view.findViewById(R.id.meeting_add_date);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ____DatePicker");
                showDatePickerDialog(Constants.NEW_MEETING);
            }
        });
        // Dismiss dialog
        ImageButton dismissDialog = view.findViewById(R.id.meeting_add_cancel_btn);
        dismissDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "dismissDialogNewMeeting: fired");
                Fragment frgm = getFragmentManager() != null ? getFragmentManager().findFragmentByTag(Constants.NEW_MEETING) : null;
                if (frgm != null) {
                    DialogFragment df = (DialogFragment) frgm;
                    df.dismiss();
                }
            }
        });
    }

    /**
     * Display and pick user choice for date of meeting.
     * @param title of the Dialog
     */
    private void showDatePickerDialog(String title) {
        DatePickerFragment newFragment = new DatePickerFragment();
        if (getFragmentManager() != null) {
            newFragment.show(getFragmentManager(), title);
        }
    }

    private void configureHallPikerSpinner(Context context, View view) {
        ArrayList<HallItem> hallList = Tools.initHallSpinnerList();
        Spinner spinnerHall = view.findViewById(R.id.meeting_add_hall);
        HallSpinnerAdapter adapter = new HallSpinnerAdapter(mContext, hallList);
        spinnerHall.setAdapter(adapter);
        spinnerHall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HallItem clickedHall = (HallItem) parent.getItemAtPosition(position);
                String clickedHallName = clickedHall.getHallName();
                // TODO change toast to get data for created a new meeting (hall-choice)
                Toast.makeText(mContext
                        ,"Clicked: "+clickedHallName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void configureHourPickerSpinner(Context context, View view) {
        // TODO change to DI with filters accordingly to possibility of reservation of hall
        ArrayList<Hour> hoursList = Tools.initHourSpinnerList();
        Spinner spinnerHour = view.findViewById(R.id.meeting_add_hour_start);
        HourSpinnerAdapter hoursAdapter = new HourSpinnerAdapter(mContext, hoursList);
        spinnerHour.setAdapter(hoursAdapter);
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Hour clickedHoursRow = (Hour) parent.getItemAtPosition(position);
                String clickedHour = clickedHoursRow.getHour();
                // TODO change toast to get data for created a new meeting (hall-choice)
                Toast.makeText(mContext
                        ,"Clicked: "+clickedHour, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    public void updateDateView(date) {
//        TextView mAddDate = mView.findViewById(R.id.meeting_add_date);
//        if (mAddDate != null)
//            mAddDate.setText(date);
//    }
    @OnClick(R.id.meeting_add_create_btn)
    void createMeeting() {
        System.out.println("new Meeting");
    }
}
