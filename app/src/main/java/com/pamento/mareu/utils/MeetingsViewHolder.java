package com.pamento.mareu.utils;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pamento.mareu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

class MeetingsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_list_meeting_avatar) CircleImageView mMeetingAvatar;
    @BindView(R.id.item_list_meeting_title) TextView mMeetingTitle;
    @BindView(R.id.item_list_meeting_date) TextView mMeetingDate;
    @BindView(R.id.item_list_meeting_hour) TextView mMeetingHourStart;
    @BindView(R.id.item_list_meeting_hour_end) TextView mMeetingHourEnd;
    @BindView(R.id.item_list_meeting_hall) TextView mMeetingHall;
    @BindView(R.id.item_list_meeting_participants_btn) TextView mMeetingParticipantsCollapsible;
    @BindView(R.id.item_list_meeting_participants) TextView mMeetingParticipants;
    @BindView(R.id.item_list_delete_button) ImageButton mDeleteMeetingButton;
    @BindView(R.id.item_list_view_holder) ConstraintLayout mItemListViewHolder;

    MeetingsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
