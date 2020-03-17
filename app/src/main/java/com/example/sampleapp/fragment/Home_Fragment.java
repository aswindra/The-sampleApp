package com.example.sampleapp.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.sampleapp.Basics.HomeActivity;
import com.example.sampleapp.R;
import com.example.sampleapp.Utils.UserProfileManager;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Home_Fragment extends Fragment {
    // Store instance variables
    private String title, current_time_display = "", diff = "";
    private String c_date, c_year;
    private int page, start_time = 0, end_time = 0, check_punch = 0, time_status = 0;
    private TextView day, year, in_out_button, login_status, status_indicator, hours_worked, login_title, login_time, wishes;
    private DigitalClock current_time;
    private RelativeLayout status_layout, attendance_layout,admin_layout;
    boolean in_out = true;

    // newInstance constructor for creating fragment with arguments
    public Home_Fragment newInstance(int page, String title) {
        Home_Fragment fragmentFirst = new Home_Fragment();
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

    private void forgreetings() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            wishes.setText("Good Morning,");
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            wishes.setText("Good Afternoon,");
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            wishes.setText("Good Evening,");
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            wishes.setText("Good Night,");
        }
    }


    private void Initialize(View view) {
        page = getArguments().getInt("someInt", 0);
        day = (TextView) view.findViewById(R.id.toolbar_date);
        year = (TextView) view.findViewById(R.id.toolbar_month);
        in_out_button = (TextView) view.findViewById(R.id.fun_button);
        login_status = (TextView) view.findViewById(R.id.login_status);
        status_indicator = (TextView) view.findViewById(R.id.status_indicator);
        hours_worked = (TextView) view.findViewById(R.id.total_count);
        current_time = (DigitalClock) view.findViewById(R.id.current_time);
        login_title = (TextView) view.findViewById(R.id.login_title);
        login_time = (TextView) view.findViewById(R.id.login_time);
        status_layout = (RelativeLayout) view.findViewById(R.id.status_layout);
        attendance_layout = (RelativeLayout) view.findViewById(R.id.attendance_layout);
        admin_layout = (RelativeLayout) view.findViewById(R.id.admin_layout);
        wishes = (TextView) view.findViewById(R.id.wishes);
        forgreetings();
    }

    private void check_user() {
        if (UserProfileManager.getUser(getActivity()).equals("1")) {
            //Admin
            attendance_layout.setVisibility(View.GONE);
            admin_layout.setVisibility(View.VISIBLE);

        } else if (UserProfileManager.getUser(getActivity()).equals("2")) {
            //Manager
            attendance_layout.setVisibility(View.VISIBLE);
        } else if (UserProfileManager.getUser(getActivity()).equals("3")) {
            //Technician
            attendance_layout.setVisibility(View.VISIBLE);
        } else {
            //Admin
            attendance_layout.setVisibility(View.VISIBLE);
        }
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        Initialize(view);

        check_user();

        day.setText(c_date);
        year.setText(c_year);

        in_out_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if (in_out == true) {
                    login_status.setText("In");
                    in_out_button.setText("Out");
                    status_indicator.setBackground(getResources().getDrawable(R.drawable.round_bg_green));
                    in_out_button.setBackground(getResources().getDrawable(R.drawable.round_bg_red));

                    getcurrentTime();
                    status_layout.setVisibility(View.VISIBLE);
                    login_title.setVisibility(View.VISIBLE);
                    login_time.setVisibility(View.VISIBLE);
                    hours_worked.setVisibility(View.GONE);
                    login_time.setText(current_time_display);
                    check_punch = 0;
                    in_out = false;
                } else {
                    login_status.setText("Out");
                    in_out_button.setText("In");
                    status_indicator.setBackground(getResources().getDrawable(R.drawable.round_bg_red));
                    in_out_button.setBackground(getResources().getDrawable(R.drawable.round_bg_green));
                    check_punch = 1;
                    getcurrentTime();
                    login_title.setVisibility(View.GONE);
                    login_time.setVisibility(View.GONE);
                    hours_worked.setVisibility(View.VISIBLE);
                    in_out = true;
                }

            }
        });

        return view;
    }

    private void getdiff_Time() {
        long mills = end_time - start_time;
        int hours = (int) ((1000 * 60 * 60) / mills);
        int mins = (int) (((1000 * 60) / mills) / 60);

        if (hours == 0 && mins > 0) {
            time_status = 1;
            diff = String.valueOf(mins); // update
        } else if (hours > 0) {
            time_status = 2;
            diff = String.valueOf(hours); // update
        }

        if (time_status == 1) {
            hours_worked.setText("You have worked " + diff + "mins");
        } else if (time_status == 2) {
            hours_worked.setText("You have worked " + diff + "hrs");
        } else {
            hours_worked.setText("Invalid time limit");
        }

    }

//    double roundTwoDecimals(double d)
//    {
//        DecimalFormat twoDForm = new DecimalFormat("#.##");
//        back Double.valueOf(twoDForm.format(d));
//    }

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

    private void getcurrentTime() {
        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
        current_time_display = sdf.format(currentTime);

        SimpleDateFormat diff = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

        if (check_punch == 0) {
            String tm = String.valueOf(diff.format(currentTime));
            String arr[] = tm.split(":");
            String m1 = arr[0];
            String m2 = arr[1];
            String m3 = arr[2];
            start_time = Integer.parseInt(m1 + m2 + m3);

        } else {
            String tm = String.valueOf(diff.format(currentTime));
            String arr[] = tm.split(":");
            String m1 = arr[0];
            String m2 = arr[1];
            String m3 = arr[2];
            end_time = Integer.parseInt(m1 + m2 + m3);
            getdiff_Time();

        }
        System.out.println("Currenttttime => " + current_time_display + "   " + start_time + "--" + end_time);
    }

}
