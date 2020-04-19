package com.pamento.mareu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pamento.mareu.R;
import com.pamento.mareu.events.DeleteMeetingEvent;
import com.pamento.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsViewHolder> {

    private List<Meeting> mMeetings;
    private Context mContext;

    public MeetingsRecyclerViewAdapter(List<Meeting> meetings, Context context) {
        mMeetings = meetings;
        mContext = context;
    }

    @NonNull
    @Override
    public MeetingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_meeting, parent, false);
        return new MeetingsViewHolder(view);
    }

    /**
     * The helper for Glide plugin.
     *
     * @param imageName the name of hall for the meeting
     * @return image from resource to display in avatar
     */
    private int getImage(String imageName) {
        return mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingsViewHolder holder, int position) {
        final boolean[] participantsIsVisible = {false};
        final Meeting meeting = mMeetings.get(position);
        String hallName = Tools.hallName(meeting.getHall());

        Glide.with(mContext).load(getImage(meeting.getHall())).into(holder.mMeetingAvatar);
        holder.mMeetingTitle.setText(meeting.getTitle());
        holder.mMeetingDate.setText(meeting.getDate());
        holder.mMeetingHourStart.setText(meeting.getHourStart());
        String hourEndOfMeeting = holder.itemView.getContext().getString(R.string.hour_end, meeting.getHourEnd());
        holder.mMeetingHourEnd.setText(hourEndOfMeeting);
        holder.mMeetingHall.setText(hallName);
        // collapsing list of participants
        List<String> parts = meeting.getParticipants();
        StringBuilder participants = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            participants.append(parts.get(i)).append("\n");
        }
        holder.mMeetingParticipants.setText(participants.toString());
        holder.mDeleteMeetingButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteMeetingEvent(meeting)));
        holder.mMeetingParticipantsCollapsible.setOnClickListener(v -> {
            if (participantsIsVisible[0]) {
                holder.mMeetingParticipants.setVisibility(View.GONE);
                holder.mMeetingParticipantsCollapsible
                        .setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0);
                participantsIsVisible[0] = false;
            } else {
                holder.mMeetingParticipants.setVisibility(View.VISIBLE);
                holder.mMeetingParticipantsCollapsible
                        .setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_black_24dp, 0);
                participantsIsVisible[0] = true;
            }
        });
        holder.mMeetingParticipants.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}
