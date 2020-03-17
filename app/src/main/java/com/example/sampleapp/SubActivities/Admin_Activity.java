package com.example.sampleapp.SubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.R;

public class Admin_Activity extends AppCompatActivity {
    private RelativeLayout Back,company;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        Initialize();
        title.setText("Admin");

        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Activity.this,Branch_Activity.class));
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Admin_Activity.this.finish();
            }
        });
    }

    private void Initialize() {
        Back=(RelativeLayout)findViewById(R.id.back);
        company=(RelativeLayout)findViewById(R.id.company_a);
        title=(TextView)findViewById(R.id.toolbar_title);
    }
}
