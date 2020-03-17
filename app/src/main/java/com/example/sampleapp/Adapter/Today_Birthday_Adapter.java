package com.example.sampleapp.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Today_Birthday_Adapter extends RecyclerView.Adapter<Today_Birthday_Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<HashMap<String, String>> boardSpaceitems = new ArrayList<>();


    public Today_Birthday_Adapter(Context context, ArrayList<HashMap<String, String>> boardSpaceitems) {
        this.context = context;
        this.boardSpaceitems = boardSpaceitems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView need_name, need_edu,send_msg;

        public MyViewHolder(View view) {
            super(view);
            need_edu = (TextView) view.findViewById(R.id.need_edu);
            need_name = (TextView) view.findViewById(R.id.need_name);
            send_msg = (TextView) view.findViewById(R.id.send);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bday_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.need_name.setText(boardSpaceitems.get(position).get("name"));
        holder.need_edu.setText(boardSpaceitems.get(position).get("date") + "    " + boardSpaceitems.get(position).get("branch"));

        holder.send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getDialog();
            }
        });
    }

    private void getDialog() {
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        dialog.setContentView(R.layout.birthday_msg_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        RelativeLayout confirm = (RelativeLayout) dialog.findViewById(R.id.confirm);
        RelativeLayout cancel = (RelativeLayout) dialog.findViewById(R.id.decline);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public int getItemCount() {
        return boardSpaceitems.size();
    }
}
