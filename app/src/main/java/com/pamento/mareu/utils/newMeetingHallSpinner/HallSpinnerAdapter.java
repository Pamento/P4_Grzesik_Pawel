package com.pamento.mareu.utils.newMeetingHallSpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.pamento.mareu.R;

import java.util.List;

public class HallSpinnerAdapter extends ArrayAdapter<HallItem> {

    public HallSpinnerAdapter(Context context, List<HallItem> HallList) {
        super(context,0,HallList);
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
        CardView cv = convertView.findViewById(R.id.spinner_cv_hall);
        cv.setRadius(10.9f);
        ImageView mImageHall = convertView.findViewById(R.id.spinner_image_hall);

        HallItem currentHall = getItem(position);

        if (currentHall != null) {
            mImageHall.setImageResource(currentHall.getHallImage());
        }
        return convertView;
    }
}
