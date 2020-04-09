package com.pamento.mareu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pamento.mareu.ListMeetingActivity;
import com.pamento.mareu.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewMeetingDialog extends DialogFragment {

    @BindView(R.id.dialog_add_title)
    EditText mAddTitle;
    @BindView(R.id.dialog_add_date) EditText mAddDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_new_meeting, container, false);
        ButterKnife.bind((ListMeetingActivity) Objects.requireNonNull(getActivity())); // ButterKnife bind only activity ?
        return view;
    }
}
