package com.example.sampleapp.Basics;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.sampleapp.R;
import com.example.sampleapp.Utils.Custom_ViewPager;
import com.example.sampleapp.Utils.UserProfileManager;
import com.example.sampleapp.fragment.Events_Fragment;
import com.example.sampleapp.fragment.Home_Fragment;
import com.example.sampleapp.fragment.More_Fragment;


public class HomeActivity extends FragmentActivity {
    private Custom_ViewPager viewpager;
    private Home_Fragment home_fragment;
    private More_Fragment more_fragment;
    private Events_Fragment event_fragment;
    private LinearLayout home, events, more, notifications;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private ImageView homeimage, sublayoutimage, notificationimage, menuimage;
    int check = 0;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activty);

        Initialize();

        firstFragment();
        check_user();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeimage.setImageResource(R.mipmap.home_active_v2);
                sublayoutimage.setImageResource(R.mipmap.sublayer_inactive_v2);
                menuimage.setImageResource(R.mipmap.menu_inactive_v2);
                notificationimage.setImageResource(R.mipmap.notification_inactive_v2);
                viewpager.setCurrentItem(0, false);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeimage.setImageResource(R.mipmap.home_inactive_v2);
                sublayoutimage.setImageResource(R.mipmap.sublayer_active_v2);
                menuimage.setImageResource(R.mipmap.menu_inactive_v2);
                notificationimage.setImageResource(R.mipmap.notification_inactive_v2);
                viewpager.setCurrentItem(1, false);
            }
        });


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeimage.setImageResource(R.mipmap.home_inactive_v2);
                sublayoutimage.setImageResource(R.mipmap.sublayer_inactive_v2);
                menuimage.setImageResource(R.mipmap.menu_active_v2);
                notificationimage.setImageResource(R.mipmap.notification_inactive_v2);
                viewpager.setCurrentItem(3, false);

            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeimage.setImageResource(R.mipmap.home_inactive_v2);
                sublayoutimage.setImageResource(R.mipmap.sublayer_inactive_v2);
                notificationimage.setImageResource(R.mipmap.notification_active_v2);
                menuimage.setImageResource(R.mipmap.menu_inactive_v2);
                if (check == 1) {
                    getDialog();
                } else {
                    Toast.makeText(HomeActivity.this, "No Notification's yet!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        homeimage.setImageResource(R.mipmap.home_active_v2);
        sublayoutimage.setImageResource(R.mipmap.sublayer_inactive_v2);
        notificationimage.setImageResource(R.mipmap.notification_inactive_v2);
        menuimage.setImageResource(R.mipmap.menu_inactive_v2);


        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                hideKeyboard(HomeActivity.this);
            }

            @Override
            public void onPageSelected(int position) {
                hideKeyboard(HomeActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                hideKeyboard(HomeActivity.this);
            }
        });

    }


    public void firstFragment() {

        fragmentTransaction.setCustomAnimations(0, 0);
        viewpager = (Custom_ViewPager) findViewById(R.id.viewPager);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }


    public void secondFragment() {
        fragmentTransaction.setCustomAnimations(0, 0);
        viewpager = (Custom_ViewPager) findViewById(R.id.viewPager);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        viewpager.setCurrentItem(1);
    }

    private void check_user() {
        if (UserProfileManager.getUser(HomeActivity.this).equals("1")) {
            //Admin
            check=0;
        } else if (UserProfileManager.getUser(HomeActivity.this).equals("2")) {
            //Manager
            check=0;
        } else if (UserProfileManager.getUser(HomeActivity.this).equals("3")) {
            //Technician
           check=1;
        } else {
            //Normal
            check=0;
        }
    }

    private void Initialize() {

        home = (LinearLayout) findViewById(R.id.homelayout_v2);
        events = (LinearLayout) findViewById(R.id.sublayout_v2);
        more = (LinearLayout) findViewById(R.id.menulayout_v2);
        notifications = (LinearLayout) findViewById(R.id.notificationlayout_v2);
        notificationimage = (ImageView) findViewById(R.id.imageView3);
        homeimage = (ImageView) findViewById(R.id.imageView);
        sublayoutimage = (ImageView) findViewById(R.id.imageView2);
        menuimage = (ImageView) findViewById(R.id.imageView4);

        //create the fragment instance for the top fragment
        home_fragment = new Home_Fragment();
        more_fragment = new More_Fragment();
        event_fragment = new Events_Fragment();
    }


    private void getDialog() {
        final Dialog dialog = new Dialog(HomeActivity.this, R.style.DialogTheme);
        dialog.setContentView(R.layout.notificatoin_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        RelativeLayout confirm = (RelativeLayout) dialog.findViewById(R.id.confirm);
        RelativeLayout cancel = (RelativeLayout) dialog.findViewById(R.id.decline);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationimage.setImageResource(R.mipmap.notification_inactive_v2);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationimage.setImageResource(R.mipmap.notification_inactive_v2);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return home_fragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return event_fragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return more_fragment.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }
    }


    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
