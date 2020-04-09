package com.pamento.mareu.utils;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pamento.mareu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_meeting_avatar) ImageView mMeetingAvatar;
    @BindView(R.id.item_list_meeting_title) TextView mMeetingTitle;
    @BindView(R.id.item_list_meeting_date) TextView mMeetingDate;
    @BindView(R.id.item_list_meeting_hall) TextView mMeetingHall;
    @BindView(R.id.item_list_meeting_participants) TextView mMeetingParticipants;
    @BindView(R.id.item_list_delete_button) Button mDeleteMeetingButton;
    @BindView(R.id.item_list_view_holder) CoordinatorLayout mItemListViewHolder;

    public MeetingsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
