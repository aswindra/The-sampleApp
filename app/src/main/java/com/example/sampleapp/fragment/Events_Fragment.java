package com.example.sampleapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.Adapter.Events_Adapter;
import com.example.sampleapp.Adapter.Today_Birthday_Adapter;
import com.example.sampleapp.Adapter.Upcoming_Birthdays_Adapter;
import com.example.sampleapp.R;
import com.example.sampleapp.SubActivities.Branches_Activity;
import com.example.sampleapp.SubActivities.Admin_Activity;
import com.example.sampleapp.Utils.UserProfileManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Events_Fragment extends Fragment {
    private String title;
    private String c_date, c_year;
    private TextView day, year;
    private int page;
    private RelativeLayout admin, branches, shadow_layout,menu;
    private ImageView open, close;
    Boolean isopen = true;
    private RecyclerView today_recycler, up_coming_recycler, events_recycler;

    // newInstance constructor for creating fragment with arguments
    public static Events_Fragment newInstance(int page, String title) {
        Events_Fragment fragmentFirst = new Events_Fragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getcurrentdate();

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_fragment, container, false);

        Initialize(view);

        check_user();

        day.setText(c_date);
        year.setText(c_year);


        ArrayList<HashMap<String, String>> dummy_datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> datas = new HashMap<>();
            datas.put("date", "01/01/2020");
            datas.put("name", "Aswin");
            datas.put("branch", "Branch_A");
            dummy_datas.add(datas);
        }

        Today_Birthday_Adapter today_birthday = new Today_Birthday_Adapter(getContext(), dummy_datas);
        today_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        today_recycler.setAdapter(today_birthday);

        Upcoming_Birthdays_Adapter upcoming_birthdays_adapter = new Upcoming_Birthdays_Adapter(getContext(), dummy_datas);
        up_coming_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        up_coming_recycler.setAdapter(upcoming_birthdays_adapter);

        Events_Adapter events_adapter = new Events_Adapter(getContext(), dummy_datas);
        events_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        events_recycler.setAdapter(events_adapter);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserProfileManager.getUser(getActivity()).equals("1")) {
                    //Admin
                    startActivity(new Intent(getActivity(), Admin_Activity.class));
                } else{
                    Toast.makeText(getActivity(), "You don't have Access", Toast.LENGTH_SHORT).show();
                }
            }
        });

        branches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Branches_Activity.class));
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isopen) {
                    shadow_layout.setVisibility(View.VISIBLE);
                    close.setVisibility(View.VISIBLE);
                    open.setVisibility(View.INVISIBLE);
                    admin.setVisibility(View.VISIBLE);
                    branches.setVisibility(View.VISIBLE);
                    isopen = false;
                } else {
                    shadow_layout.setVisibility(View.GONE);
                    close.setVisibility(View.INVISIBLE);
                    open.setVisibility(View.VISIBLE);
                    admin.setVisibility(View.INVISIBLE);
                    branches.setVisibility(View.INVISIBLE);
                    isopen = true;
                }

//                isopen = false;
            }
        });

        return view;
    }

    private void Initialize(View view) {
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        admin = (RelativeLayout) view.findViewById(R.id.admin);
        branches = (RelativeLayout) view.findViewById(R.id.branches);
        menu = (RelativeLayout) view.findViewById(R.id.menu);
        day = (TextView) view.findViewById(R.id.toolbar_date);
        year = (TextView) view.findViewById(R.id.toolbar_month);
        open = (ImageView) view.findViewById(R.id.open);
        close = (ImageView) view.findViewById(R.id.close);
        shadow_layout = (RelativeLayout) view.findViewById(R.id.shadow_layout);
        today_recycler = (RecyclerView) view.findViewById(R.id.today_recycler);
        up_coming_recycler = (RecyclerView) view.findViewById(R.id.upcoming_recycler);
        events_recycler = (RecyclerView) view.findViewById(R.id.events_recycler);
    }

    private void check_user() {
        if (UserProfileManager.getUser(getActivity()).equals("1")) {
            //Admin
            menu.setVisibility(View.VISIBLE);
        } else if (UserProfileManager.getUser(getActivity()).equals("2")) {
            //Manager
            menu.setVisibility(View.VISIBLE);
        } else if (UserProfileManager.getUser(getActivity()).equals("3")) {
            //Technician
              //Do nothing
        } else {
            //Normal
            //Do nothing
        }
    }

    private String getcurrentdate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("E/dd/MMM yyyy");
        String formattedDate = df.format(c);
        Log.d("pdate", formattedDate);

        String s = formattedDate;
        String arr[] = s.split("/");
        System.out.println("Day = " + arr[0]);
        System.out.println("Date = " + arr[1]);
        System.out.println("Year = " + arr[2]);

        String m1 = arr[0];
        c_date = arr[1];
        c_year = arr[2];

        return m1;
    }
}
