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

public class Branch_Adapter extends RecyclerView.Adapter<Branch_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> boardSpaceitems = new ArrayList<>();


    public Branch_Adapter(ArrayList<HashMap<String, String>> dummy_datas) {
        this.boardSpaceitems = dummy_datas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public Branch_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_data, parent, false);
        return new Branch_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Branch_Adapter.MyViewHolder holder, final int position) {

    }


    @Override
    public int getItemCount() {
        return boardSpaceitems.size();
    }
}
