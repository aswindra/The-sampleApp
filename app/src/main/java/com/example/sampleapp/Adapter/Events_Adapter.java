package com.example.sampleapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;
import com.example.sampleapp.SubActivities.Event_Details_Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Events_Adapter extends RecyclerView.Adapter<Events_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> boardSpaceitems = new ArrayList<>();
    Date deadLinedate, currentdate;
    String dateConverted;
    String currentdatevalue;
    String month_sep, date_sep, month_rec, date_rec;
    String dateDiff = "";

    public Events_Adapter(Context context, ArrayList<HashMap<String, String>> boardSpaceitems) {
        this.context = context;
        this.boardSpaceitems = boardSpaceitems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView need_name, need_edu,check_details;

        public MyViewHolder(View view) {
            super(view);
            need_edu = (TextView) view.findViewById(R.id.event_venue);
            need_name = (TextView) view.findViewById(R.id.eventLoc);
            check_details = (TextView) view.findViewById(R.id.check_details);
        }
    }

    @Override
    public Events_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_layout, parent, false);
        return new Events_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Events_Adapter.MyViewHolder holder, final int position) {
        holder.need_name.setText(boardSpaceitems.get(position).get("name"));
        holder.need_edu.setText(boardSpaceitems.get(position).get("date") + "    " + boardSpaceitems.get(position).get("branch"));

        holder.check_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Event_Details_Activity.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return boardSpaceitems.size();
    }
}
