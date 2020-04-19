package com.pamento.mareu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pamento.mareu.R;
import com.pamento.mareu.events.DeleteMeetingEvent;
import com.pamento.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsViewHolder> {

    // For data
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

    private int getImage(String imageName) {

        return mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingsViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);
        // TODO avatar
        String hallName = Tools.hallName(meeting.getHall());

        Glide.with(mContext).load(getImage(meeting.getHall())).into(holder.mMeetingAvatar);
        holder.mMeetingTitle.setText(meeting.getTitle());
        holder.mMeetingDate.setText(meeting.getDate());
        holder.mMeetingHall.setText(hallName);
        // TODO collapsing list of participants
        List<String> parts = meeting.getParticipants();
        StringBuilder participants = new StringBuilder();
        for (String part: parts) {
            participants.append(part).append("\n");
        }
        holder.mMeetingParticipants.setText(participants.toString());// initially participants is the List array
        holder.mItemListViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, meeting.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        // OnClickListener set on button 'Delete'
        holder.mDeleteMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                Toast.makeText(mContext, "Delete button fired.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}
