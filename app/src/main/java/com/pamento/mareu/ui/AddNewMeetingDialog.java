package com.pamento.mareu.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pamento.mareu.ListMeetingActivity;
import com.pamento.mareu.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewMeetingDialog extends DialogFragment {

    @BindView(R.id.dialog_add_title)
    EditText mAddTitle;
    @BindView(R.id.dialog_add_date)
    EditText mAddDate;
    @BindView(R.id.dialog_add_create_btn)
    Button mButton;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_new_meeting, container, false);
        ButterKnife.bind((ListMeetingActivity) Objects.requireNonNull(getActivity())); // ButterKnife bind only activity ?
        return view;
    }

    // To display full screen
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @OnClick(R.id.dialog_add_create_btn)
    public void createMeeting() {
        System.out.println("new Meeting");
    }
}
