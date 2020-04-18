package com.pamento.mareu.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.pamento.mareu.utils.Constants;
import com.pamento.mareu.utils.Tools;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallItem;
import com.pamento.mareu.utils.newMeetingHallSpinner.HallSpinnerAdapter;
import com.pamento.mareu.utils.newMeetingHourSpinner.Hour;
import com.pamento.mareu.utils.newMeetingHourSpinner.HourSpinnerAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewMeetingDialog extends DialogFragment {
    private static final String TAG = "____DiALOG___newMeeting";

    // Action Button
    @BindView(R.id.meeting_add_cancel_btn) ImageButton mCancel;
    @BindView(R.id.meeting_add_create_btn) ImageButton mSave;
    //@BindView(R.id.meeting_participants_btn) ImageButton mAddParticipant;
    // View
    @BindView(R.id.meeting_add_title) EditText mAddMeetingTitle;
    @BindView(R.id.meeting_add_date) TextView mAddMeetingDate;
    @BindView(R.id.meeting_add_hour_start) Spinner mAddMeetingHourStart;
    @BindView(R.id.meeting_add_hour_end) Spinner mAddMeetingHourEnd;
    @BindView(R.id.meeting_add_hall) Spinner mAddMeetingHall;
    @BindView(R.id.meeting_add_participants_edit) EditText mEditParticipants;
    @BindView(R.id.meeting_list_participants) ChipGroup mListParticipants;

    private ApiService mApiService;
    private Context mContext;
    // Variable to submit
    private String mHall;
    private String mFromHour;
    private String mToHour;
    private String mDate;
    private String mParticipants;

    // REQUIRED EMPTY CONSTRUCTOR
    public AddNewMeetingDialog() {
    }

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
        // Fetch arguments from bundle and set title
//        String title = getArguments() != null ? getArguments().getString("title", "Enter Name") : null;
//        Objects.requireNonNull(getDialog()).setTitle(title);

        // Show soft keyboard automatically and request focus to field
//        mAddMeetingTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//            }
//        });
        //mAddMeetingTitle.requestFocus();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mContext = getContext();
        // Date picker DialogFragment
        configureDatePickerDialog();
        // Hall Spinner =========================
        configureHallPikerSpinner(view);
        // START Hour Spinner =========================
        configureHourPickerSpinner(view);
        // END Hour Spinner ===========================
        configureHourEndPickerSpinner(view);
        // Listen mEditorParticipant
        //mEditParticipants.setOnEditorActionListener(editorListener);
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window to resize the Dialog view to full screen
        WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        // Call super onResume after sizing
        super.onResume();
    }

    // Dismiss dialog
    @OnClick(R.id.meeting_add_cancel_btn)
    void cancelDialog() {
        Log.d(TAG, "dismissDialogNewMeeting: fired");
        Fragment fm = getFragmentManager() != null ? getFragmentManager().findFragmentByTag(Constants.NEW_MEETING) : null;
        if (fm != null) {
            DialogFragment df = (DialogFragment) fm;
            df.dismiss();
        }
    }

    private void configureDatePickerDialog() {
        mAddMeetingDate.setOnClickListener(v -> showDatePickerDialog(Constants.NEW_MEETING));
    }

    /**
     * Display and pick user choice for date of meeting.
     *
     * @param title of the Dialog
     */
    private void showDatePickerDialog(String title) {
        DatePickerFragment newDatePickerFragment = new DatePickerFragment();
        newDatePickerFragment.setTargetFragment(AddNewMeetingDialog.this, Constants.TARGET_FRAGMENT_REQUEST_CODE);
        if (getFragmentManager() != null) {
            newDatePickerFragment.show(getFragmentManager(), title);
        }
    }

    private void configureHallPikerSpinner(View view) {
        ArrayList<HallItem> hallList = Tools.initHallSpinnerList();
        HallSpinnerAdapter adapter = new HallSpinnerAdapter(mContext, hallList);
        mAddMeetingHall.setAdapter(adapter);
        mAddMeetingHall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HallItem clickedHall = (HallItem) parent.getItemAtPosition(position);
                mHall = clickedHall.getHallName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configureHourPickerSpinner(View view) {
        // TODO change to DI with filters accordingly to possibility of reservation of hall
        ArrayList<Hour> hoursList = Tools.initHourSpinnerList();

        HourSpinnerAdapter hoursAdapter = new HourSpinnerAdapter(mContext, hoursList);
        mAddMeetingHourStart.setAdapter(hoursAdapter);
        mAddMeetingHourStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Hour clickedHoursRow = (Hour) parent.getItemAtPosition(position);
                mFromHour = clickedHoursRow.getHour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configureHourEndPickerSpinner(View view) {
        // TODO change to DI with filters accordingly to possibility of reservation of hall
        ArrayList<Hour> hoursList = Tools.initHourSpinnerList();

        Spinner spinnerHour = view.findViewById(R.id.meeting_add_hour_end);
        HourSpinnerAdapter hoursAdapter = new HourSpinnerAdapter(mContext, hoursList);
        spinnerHour.setAdapter(hoursAdapter);
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Hour clickedHoursRow = (Hour) parent.getItemAtPosition(position);
                mToHour = clickedHoursRow.getHour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @OnClick(R.id.meeting_participants_btn)
    void addChip() {
        final Chip chip = new Chip(Objects.requireNonNull(getContext()));
        ChipDrawable chipDrawable = ChipDrawable.createFromResource(getContext(),R.xml.item_chip_participant);
        chip.setChipDrawable(chipDrawable);
        chip.setText(mEditParticipants.getText());
        chip.setOnCloseIconClickListener(v -> mListParticipants.removeView(chip));
        chip.setElevation(10.0f);
        mListParticipants.addView(chip);
        // TODO add emails form chips to mParticipants
        mEditParticipants.setText("");
    }

    /**
     * Method to retrieve the date from {@link DatePickerFragment}
     * with use of newIntent method.
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        object with data(the date)
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == Constants.TARGET_FRAGMENT_REQUEST_CODE) {
            // TODO in place of null if condition is false: make an static class showMessage with SnackBar+message
            // ex.: snackBarMessage(int typeMessage:[error,info],String message)
            String date = data != null ? data.getStringExtra(Constants.EXTRA_DATE_PICKER_DIALOG) : null;
            mAddMeetingDate.setText(date);
            mDate = date;
        }
    }

    static Intent newIntent(String message) {
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_DATE_PICKER_DIALOG, message);
        return intent;
    }

//    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
//        @Override
//        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//            switch (actionId){
//                case EditorInfo.IME_ACTION_GO:
//                    addChip(mEditParticipants.getText().toString());
//                    Log.d(TAG, "onEditorAction: "+mEditParticipants.getText().toString());
//                    break;
//            }
//            return false;
//        }
//    };

    /**
     * TODO
     * before save hew meeting:
     * example to set 'save' button disable:
     *     private void init() {
     *         mNeighbourImage = randomImage();
     *         Glide.with(this).load(mNeighbourImage).placeholder(R.drawable.ic_account)
     *                 .apply(RequestOptions.circleCropTransform()).into(avatar);
     *         Objects.requireNonNull(nameInput.getEditText()).addTextChangedListener(new TextWatcher() {
     *             @Override
     *             public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
     *             @Override
     *             public void onTextChanged(CharSequence s, int start, int before, int count) { }
     *             @Override
     *             public void afterTextChanged(Editable s) {
     *                 addButton.setEnabled(s.length() > 0);
     *             }
     *         });
     *
     *     }
     * Or check if whole form is valid (?)
     */
    @OnClick(R.id.meeting_add_create_btn)
    // TODO participants go to be List<String> or just String ?
    void createMeeting() {
        Log.d(TAG, "createMeeting: ");
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
        cancelDialog();
    }


}
