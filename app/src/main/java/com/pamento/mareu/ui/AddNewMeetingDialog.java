package com.pamento.mareu.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pamento.mareu.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class AddNewMeetingDialog extends DialogFragment {
    private static final String TAG = "____DiALOG___newMeeting";


//    @BindView(R.id.dialog_add_hall) ImageView mAddHall;
//    @BindView(R.id.dialog_add_title) EditText mAddTitle;
    @BindView(R.id.dialog_add_date) TextInputEditText mAddDate;
//    @BindView(R.id.dialog_add_hour) TextInputEditText mAddHour;
//    @BindView(R.id.dialog_add_participants) EditText mAddParticipants;
//    @BindView(R.id.dialog_add_create_btn) MaterialButton mSaveMeeting;
    //boolean isLargeLayout;

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
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_new_meeting, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        EditText editText = (EditText) view.findViewById(R.id.dialog_add_title);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        Objects.requireNonNull(getDialog()).setTitle(title);

        TextView addDate = (TextView) view.findViewById(R.id.event_add_new_meeting);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ____DatePicker");
                showDatePickerDialog(Constants.F_NEW_MEETING);
            }
        });
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        if (getDialog() == null) {
//            Context context = getContext();
//            while (context instanceof ContextWrapper) {
//                if (context instanceof FragmentActivity) {
//                    break;
//                }
//                context = ((ContextWrapper) context).getBaseContext();
//            }
//            if (context instanceof FragmentActivity) {
//                ((FragmentActivity) context).finish();
//                return;
//            } else {
//                setShowsDialog(false);
//            }
//        }
//        super.onActivityCreated(savedInstanceState);
//    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View vChild = container.getChildAt(0);
//        Log.d(TAG, "onCreateView: "+vChild.toString());
//        View view = inflater.inflate(R.layout.dialog_new_meeting, container,false);
//        return view;
////        View view = inflater.inflate(R.layout.dialog_new_meeting, container, false);
////        ButterKnife.bind((ListMeetingActivity) Objects.requireNonNull(getActivity())); // ButterKnife bind only activity ?
////        //isLargeLayout = getResources().getBoolean(R.bool.large_layout);
////        return view;
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Log.d(TAG, "onViewCreated: O");
//        super.onViewCreated(view, savedInstanceState);
//        Log.d(TAG, "onViewCreated: super");
//        // Get field from view
//        EditText mAddTitle = (EditText) view.findViewById(R.id.dialog_add_title);
//        // Fetch arguments from bundle and set title
//        String title = getArguments().getString("title", "Enter Name");
//
//        Log.d(TAG, "onViewCreated: title: \"" + title+"\"");
////        Object dial = getDialog();
////        Log.d(TAG, "onViewCreated: dialog: "+dial);
////        // getDialog().setTitle(title);
////        // Show soft keyboard automatically and request focus to field
////        mAddTitle.requestFocus();
////        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(
////                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
////        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        // The only reason you might override this method when using onCreateView() is
//        // to modify any dialog characteristics. For example, the dialog includes a
//        // title by default, but your custom layout might not need it. So here you can
//        // remove the dialog title, but you must call the superclass to get the Dialog.
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        return dialog;
//    }

    // To display full screen
//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            int width = ViewGroup.LayoutParams.MATCH_PARENT;
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            dialog.getWindow().setLayout(width, height);
//        }
//    }

    void showDatePickerDialog(String title) {
        Log.d(TAG, "showDatePickerDialog __fired ");
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), title);
    }

    @OnClick(R.id.dialog_add_create_btn)
    void createMeeting() {
        System.out.println("new Meeting");
    }
}
