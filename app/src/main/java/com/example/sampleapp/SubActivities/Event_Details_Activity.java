package com.example.sampleapp.SubActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.R;

import java.util.Calendar;

public class Event_Details_Activity extends AppCompatActivity {
    private TextView title;
    private RelativeLayout back,add_tocalendar;
    Activity CURRENT_ACTIVITY = Event_Details_Activity.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);

        Initialize();
        title.setText("New Branch Opening");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CURRENT_ACTIVITY.finish();
            }
        });
        add_tocalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendarEvent = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", calendarEvent.getTimeInMillis());
                intent.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                intent.putExtra("title", "Sample Event");
                intent.putExtra("allDay", true);
                intent.putExtra("rule", "FREQ=YEARLY");
                startActivity(intent);
            }
        });
    }

    private void Initialize() {
        title=(TextView)findViewById(R.id.toolbar_title);
        back=(RelativeLayout)findViewById(R.id.back);
        add_tocalendar=(RelativeLayout)findViewById(R.id.add_tocalender);
    }
}
