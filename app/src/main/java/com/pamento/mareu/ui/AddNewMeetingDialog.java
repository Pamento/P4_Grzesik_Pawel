package com.pamento.mareu.ui;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewMeetingDialog extends DialogFragment {
    private static final String TAG = "____DiALOG___newMeeting";

    //    @BindView(R.id.dialog_add_hall) ImageView mAddHall;
//    @BindView(R.id.dialog_add_title) EditText mAddTitle;
    @BindView(R.id.event_add_new_meeting)
    TextView mAddDate;
    //    @BindView(R.id.dialog_add_hour) TextInputEditText mAddHour;
//    @BindView(R.id.dialog_add_participants) EditText mAddParticipants;
//    @BindView(R.id.dialog_add_create_btn) MaterialButton mSaveMeeting;
    @BindView(R.id.dialog_add_cancel)
    ImageButton mCancelDialogBtn;

    private ArrayList<HallItem> mHallList;
    private HallSpinnerAdapter mAdapter;
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
        // Get field from view
        EditText editText = (EditText) view.findViewById(R.id.dialog_add_title);
        // Fetch arguments from bundle and set title
        String title = getArguments() != null ? getArguments().getString("title", "Enter Name") : null;
        Objects.requireNonNull(getDialog()).setTitle(title);
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        actionToDo(view);

        // Hall Spinner ======= start
        mContext = getContext();
        mHallList = Tools.initHallSpinnerList();
        Spinner spinnerHall = view.findViewById(R.id.dialog_add_hall);
        mAdapter = new HallSpinnerAdapter(mContext, mHallList);
        spinnerHall.setAdapter(mAdapter);
        spinnerHall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HallItem clickedHall = (HallItem) parent.getItemAtPosition(position);
                String clickedHallName = clickedHall.getHallName();
                Toast.makeText(mContext
                ,"Clicked: "+clickedHallName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Hall Spinner ======= end
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

    @Override
    public void onStart() {
        super.onStart();
    }

    //@OnClick(R.id.dialog_add_cancel)
    private void actionToDo(View view) {
        // Display DataPicker
        TextView addDate = (TextView) view.findViewById(R.id.event_add_new_meeting);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ____DatePicker");
                showDatePickerDialog(Constants.NEW_MEETING);
            }
        });
        // Dismiss dialog
        ImageButton dismissDialog = view.findViewById(R.id.dialog_add_cancel);
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

    private void showDatePickerDialog(String title) {
        Log.d(TAG, "showDatePickerDialog __fired ");
        DatePickerFragment newFragment = new DatePickerFragment();
        if (getFragmentManager() != null) {
            newFragment.show(getFragmentManager(), title);
        }
    }

    @OnClick(R.id.dialog_add_create_btn)
    void createMeeting() {
        System.out.println("new Meeting");
    }
}
