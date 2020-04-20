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

import java.util.List;

public class HourSpinnerAdapter extends ArrayAdapter<Hour> {

    public HourSpinnerAdapter(Context context, List<Hour> HourList) {
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
                    R.layout.item_spinner_hour, parent, false
            );
        }
        TextView mHourTestView = convertView.findViewById(R.id.spinner_display_hour);

        Hour currentHour = getItem(position);

        if (currentHour != null) {
            mHourTestView.setText(currentHour.getHour());
        }
        return convertView;
    }
}
