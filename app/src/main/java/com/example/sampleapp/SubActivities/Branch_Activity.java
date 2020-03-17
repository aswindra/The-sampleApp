package com.example.sampleapp.SubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.R;

public class Branch_Activity extends AppCompatActivity {
    private RelativeLayout Back, store_details;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branch_layout);

        Initialize();
        title.setText("The Sample App");

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Branch_Activity.this.finish();
            }
        });

        store_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Branch_Activity.this, Store_Details_Activity.class));
            }
        });

    }

    private void Initialize() {
        Back = (RelativeLayout) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.toolbar_title);
        store_details = (RelativeLayout) findViewById(R.id.store_details);
    }
}
