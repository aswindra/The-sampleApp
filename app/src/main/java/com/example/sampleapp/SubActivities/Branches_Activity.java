package com.example.sampleapp.SubActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.R;

public class Branches_Activity extends AppCompatActivity {
    private RelativeLayout Back;
    private TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branches_layout);

        Initialize();

        title.setText("Branches");

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Branches_Activity.this.finish();
            }
        });
    }

    private void Initialize() {
        Back=(RelativeLayout)findViewById(R.id.back);
        title=(TextView)findViewById(R.id.toolbar_title);
    }
}
