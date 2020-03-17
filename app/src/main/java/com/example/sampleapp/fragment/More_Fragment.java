package com.example.sampleapp.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.sampleapp.Basics.LoginScreen;
import com.example.sampleapp.R;
import com.example.sampleapp.SubActivities.MyProfile_Activity;
import com.example.sampleapp.SubActivities.Status_Activity;
import com.example.sampleapp.Utils.UserProfileManager;

public class More_Fragment extends Fragment {
    private String title;
    private int page;
    private RelativeLayout myprofile_container, back, status_container, signout;
    private View view4;

    // newInstance constructor for creating fragment with arguments
    public static More_Fragment newInstance(int page, String title) {
        More_Fragment fragmentFirst = new More_Fragment();
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
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_fragments, container, false);
        Initialize(view);

        check_user();

        myprofile_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProfile_Activity.start(getActivity(), MyProfile_Activity.TYPE_VIEW);
            }
        });

        status_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Status_Activity.class));
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdialog();
            }
        });

        return view;
    }

    private void getdialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.signout_for_confromation, null);
        final AlertDialog alertforsignout = new AlertDialog.Builder(getActivity()).create();
        alertforsignout.setView(alertLayout);

        RelativeLayout confirm = (RelativeLayout) alertLayout.findViewById(R.id.confirm);
        RelativeLayout cancel = (RelativeLayout) alertLayout.findViewById(R.id.decline);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginScreen.class));
                getActivity().finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertforsignout.dismiss();
            }
        });

        alertforsignout.show();
    }

    private void check_user() {
        if (UserProfileManager.getUser(getActivity()).equals("1")) {
            //Admin

        } else if (UserProfileManager.getUser(getActivity()).equals("2")) {
            //Manager

        } else if (UserProfileManager.getUser(getActivity()).equals("3")) {
            //Technician
            view4.setVisibility(View.VISIBLE);
            status_container.setVisibility(View.VISIBLE);
        } else {
            //Admin

        }
    }

    private void Initialize(View view) {
        myprofile_container = (RelativeLayout) view.findViewById(R.id.myprofile_container);
        status_container = (RelativeLayout) view.findViewById(R.id.status_container);
        view4=(View)view.findViewById(R.id.view4);
        back = (RelativeLayout) view.findViewById(R.id.back);
        signout = (RelativeLayout) view.findViewById(R.id.sign_out_container);
    }
}
