package com.pamento.mareu.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.pamento.mareu.R;
import com.pamento.mareu.di.DI;
import com.pamento.mareu.events.RefreshRecyclerView;
import com.pamento.mareu.model.Meeting;
import com.pamento.mareu.service.ApiService;
import com.pamento.mareu.service.Resources;
import com.pamento.mareu.utils.Constants;
import com.pamento.mareu.utils.Tools;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallItem;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallSpinnerAdapter;
import com.pamento.mareu.utils.newMeetingHourSpinner.Hour;
import com.pamento.mareu.utils.newMeetingHourSpinner.HourSpinnerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewMeetingDialog extends DialogFragment {

    @BindView(R.id.coordinatorLayout) CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.meeting_add_cancel_btn) ImageButton mCancel;
    @BindView(R.id.meeting_add_create_btn) ImageButton mSave;
    @BindView(R.id.meeting_add_title) EditText mAddMeetingTitle;
    @BindView(R.id.meeting_add_date) TextView mAddMeetingDate;
    @BindView(R.id.meeting_add_hour_start) Spinner mAddMeetingHourStart;
    @BindView(R.id.meeting_add_hour_end) Spinner mAddMeetingHourEnd;
    @BindView(R.id.meeting_add_hall) Spinner mAddMeetingHall;
    @BindView(R.id.meeting_add_participants_edit) EditText mEditParticipants;
    @BindView(R.id.meeting_list_participants) ChipGroup mListParticipants;

    private ApiService mApiService;
    private Context mContext;
    private boolean isLargeLayout;
    private String mHall = "", mFromHour = "", mToHour = "", mDate = "";
    private List<String> mParticipants = new ArrayList<>();

    public AddNewMeetingDialog() { }// REQUIRED EMPTY CONSTRUCTOR

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
        mApiService = DI.getApiService();
        isLargeLayout = getResources().getBoolean(R.bool.large_layout);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_meeting, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mContext = getContext();
        configureDatePickerDialog();
        configureHallPikerSpinner();
        configureHourPickerSpinner();
        configureHourEndPickerSpinner();
    }

    @Override
    public void onResume() {
        // Display the DialogFragment in mode FullScreen only if window is small.
        if (!isLargeLayout) {
            // Get existing layout params for the window to resize the Dialog view to full screen
            WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
            // Assign window properties to fill the parent
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        }
        // Call super onResume after sizing
        super.onResume();
    }

    // Dismiss dialog
    @OnClick(R.id.meeting_add_cancel_btn)
    void cancelDialog() {
        Fragment fm = getFragmentManager() != null ? getFragmentManager().findFragmentByTag(Constants.NEW_MEETING) : null;
        if (fm != null) {
            DialogFragment df = (DialogFragment) fm;
            df.dismiss();
        }
    }

    private void configureDatePickerDialog() {
        mAddMeetingDate.setOnClickListener(v -> showDatePickerDialog());
    }

    /**
     * Display and pick user choice for date of meeting.
     */
    private void showDatePickerDialog() {
        DatePickerFragment newDatePickerFragment = new DatePickerFragment();
        newDatePickerFragment.setTargetFragment(AddNewMeetingDialog.this, Constants.TARGET_FRAGMENT_REQUEST_CODE);
        if (getFragmentManager() != null) {
            newDatePickerFragment.show(getFragmentManager(), Constants.NEW_MEETING);
        }
    }

    private void configureHallPikerSpinner() {
        List<HallItem> hallList = Resources.initHallSpinnerList();
        HallSpinnerAdapter adapter = new HallSpinnerAdapter(mContext, hallList);
        mAddMeetingHall.setAdapter(adapter);
        mAddMeetingHall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HallItem clickedHall = (HallItem) parent.getItemAtPosition(position);
                mHall = clickedHall.getHallName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { /**/ }
        });
    }

    private void configureHourPickerSpinner() {
        // TODO change to DI with filters accordingly to possibility of reservation of hall
        List<Hour> hoursList = Resources.initHourSpinnerList();
        HourSpinnerAdapter hoursAdapter = new HourSpinnerAdapter(mContext, hoursList);
        mAddMeetingHourStart.setAdapter(hoursAdapter);
        mAddMeetingHourStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Hour clickedHoursRow = (Hour) parent.getItemAtPosition(position);
                mFromHour = clickedHoursRow.getHour();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { /**/ }
        });
    }

    private void configureHourEndPickerSpinner() {
        // TODO change to DI with filters accordingly to possibility of reservation of hall
        List<Hour> hoursList = Resources.initHourSpinnerList();
        HourSpinnerAdapter hoursAdapter = new HourSpinnerAdapter(mContext, hoursList);
        mAddMeetingHourEnd.setAdapter(hoursAdapter);
        mAddMeetingHourEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Hour clickedHoursRow = (Hour) parent.getItemAtPosition(position);
                mToHour = clickedHoursRow.getHour();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { /**/ }
        });
    }

    @OnClick(R.id.meeting_participants_btn)
    void addChip() {
        mParticipants.add(mEditParticipants.getText().toString().trim());
        final Chip chip = new Chip(Objects.requireNonNull(getContext()));
        ChipDrawable chipDrawable = ChipDrawable.createFromResource(getContext(), R.xml.item_chip_participant);
        chip.setChipDrawable(chipDrawable);
        chip.setText(mEditParticipants.getText());
        chip.setOnCloseIconClickListener(v -> {
            mListParticipants.removeView(chip);
            int pos = mParticipants.indexOf(chip.getText().toString());
            if (pos >= 0) mParticipants.remove(pos);
        });
        chip.setElevation(10.0f);
        mListParticipants.addView(chip);
        mEditParticipants.setText("");
    }

    /**
     * Method to retrieve the date from {@link DatePickerFragment}
     * with use of newIntent method.
     *
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        object with data(the date)
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) { return; }
        if (requestCode == Constants.TARGET_FRAGMENT_REQUEST_CODE) {
            String sDate = data != null ? data.getStringExtra(Constants.EXTRA_DATE_PICKER_DIALOG) : null;
            if (sDate != null) mAddMeetingDate.setText(sDate);
            else Tools.showSnackBar(0,mCoordinatorLayout,Constants.ERROR_MESSAGE);
            mDate = sDate;
        }
    }

    static Intent newIntent(String message) {
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_DATE_PICKER_DIALOG, message);
        return intent;
    }

    @OnClick(R.id.meeting_add_create_btn)
    void checkRequiredFormFields() {
        // recover last participant written by the user in EditText and not submit
        if (!mEditParticipants.getText().toString().trim().equals(""))
            if (!mParticipants.contains(mEditParticipants.getText().toString().trim()))
                mParticipants.add(mEditParticipants.getText().toString().trim());

        StringBuilder message = new StringBuilder();
        if (mAddMeetingTitle.getText().toString().equals("")) { message.append(" sujet");}
        if (mDate.equals("")) { message.append(message.length() == 0 ? " date" : " ,date"); }
        if (mFromHour.equals("l'heur")) {
            message.append(message.length() == 0 ? " le début" : " ,le début");
        }
        if (mToHour.equals("l'heur")) {
            message.append(message.length() == 0 ? " la fin" : " ,la fin");
        }
        if (mHall.equals("hall_0")) {
            message.append(message.length() == 0 ? " la salle" : " ,la salle");
        }
        if (mParticipants.size() <= 1) {
            message.append(message.length() == 0 ? " les participants" : " ,les participants");
        }
        if (mFromHour.equals(mToHour)) Tools.showSnackBar(1,mCoordinatorLayout,Constants.WARNING_SAME_HOURS);
        else if (message.length() != 0) Tools.showSnackBar(1, mCoordinatorLayout, "Il manque: " + message+ ".");
        else createMeeting();
    }

    private void createMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                mAddMeetingTitle.getText().toString(),
                mDate,
                mFromHour,
                mToHour,
                mHall,
                mParticipants
        );
        mApiService.createMeeting(meeting);
        EventBus.getDefault().post(new RefreshRecyclerView(0));
        Tools.showSnackBar(0, mCoordinatorLayout,Constants.SUCCESS_ADD_MEETING);
        cancelDialog();
    }
}
