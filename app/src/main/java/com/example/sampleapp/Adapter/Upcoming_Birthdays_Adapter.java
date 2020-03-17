package com.example.sampleapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Upcoming_Birthdays_Adapter  extends RecyclerView.Adapter<Upcoming_Birthdays_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> boardSpaceitems = new ArrayList<>();
    Date deadLinedate, currentdate;
    String dateConverted;
    String currentdatevalue;
    String month_sep, date_sep, month_rec, date_rec;
    String dateDiff = "";

    public Upcoming_Birthdays_Adapter(Context context, ArrayList<HashMap<String, String>> boardSpaceitems) {
        this.context = context;
        this.boardSpaceitems = boardSpaceitems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView need_name, need_edu;

        public MyViewHolder(View view) {
            super(view);
            need_edu = (TextView) view.findViewById(R.id.need_edu);
            need_name = (TextView) view.findViewById(R.id.need_name);
        }
    }

    @Override
    public Upcoming_Birthdays_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_bday_layout, parent, false);
        return new Upcoming_Birthdays_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Upcoming_Birthdays_Adapter.MyViewHolder holder, final int position) {
        holder.need_name.setText(boardSpaceitems.get(position).get("name"));
        holder.need_edu.setText(boardSpaceitems.get(position).get("date") + "    " + boardSpaceitems.get(position).get("branch"));
    }


    @Override
    public int getItemCount() {
        return boardSpaceitems.size();
    }
}
