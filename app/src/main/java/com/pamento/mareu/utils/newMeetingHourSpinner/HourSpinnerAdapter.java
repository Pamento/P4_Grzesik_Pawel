package com.pamento.mareu.utils.newMeetingHourSpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pamento.mareu.R;

import java.util.ArrayList;

public class HourSpinnerAdapter extends ArrayAdapter<Hour> {

    public HourSpinnerAdapter(Context context, ArrayList<Hour> HourList) {
        super(context,0,HourList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return viewInit(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return viewInit(position, convertView, parent);
    }

    private View viewInit(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_spinner_hall, parent, false
            );
        }
        TextView mHourTestView = convertView.findViewById(R.id.spinner_image_hall);

        Hour currentHour = getItem(position);

        if (currentHour != null) {
            mHourTestView.setText(currentHour.getHour());
        }
        return convertView;
    }
}
