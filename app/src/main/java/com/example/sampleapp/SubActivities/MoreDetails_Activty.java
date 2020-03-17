package com.example.sampleapp.SubActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.Adapter.MoreDetails_Adapter;
import com.example.sampleapp.Adapter.Today_Birthday_Adapter;
import com.example.sampleapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MoreDetails_Activty  extends AppCompatActivity {
    private RelativeLayout Back, store_details;
    private TextView title;
    private LinearLayoutManager todaylayoutinflater;
    private RecyclerView moredetails_recycler;
    private Activity CURRENT_ACTIVITY = MoreDetails_Activty.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_details_layout);

        Initialize();
        title.setText("More Details");

        ArrayList<HashMap<String,String>> dummy_datas=new ArrayList<>();
        for (int i=0;i<10;i++){
            HashMap<String,String>datas=new HashMap<>();
            datas.put("date","01/01/2020");
            datas.put("name","Aswin");
            datas.put("branch","Branch_A");
            dummy_datas.add(datas);
        }


        MoreDetails_Adapter today_birthday=new MoreDetails_Adapter(CURRENT_ACTIVITY, dummy_datas);
        moredetails_recycler.setLayoutManager(todaylayoutinflater);
        moredetails_recycler.setAdapter(today_birthday);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CURRENT_ACTIVITY.finish();
            }
        });

    }

    private void Initialize() {
        Back = (RelativeLayout) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.toolbar_title);
        todaylayoutinflater = new LinearLayoutManager(CURRENT_ACTIVITY);
        moredetails_recycler=(RecyclerView)findViewById(R.id.more_details_recycler);
    }
}
