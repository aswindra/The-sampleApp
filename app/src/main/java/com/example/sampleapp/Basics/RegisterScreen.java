package com.example.sampleapp.Basics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterScreen extends AppCompatActivity {
    private Spinner designation,location_spinner;
    private TextView signin;
    Activity CURRENT_ACTIVITY = RegisterScreen.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        Initialize();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CURRENT_ACTIVITY,LoginScreen.class));
                CURRENT_ACTIVITY.finish();
            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Manager");
        categories.add("Technician");
        categories.add("Receptionist");
        categories.add("Tele-Caller");
        categories.add("Tele-Caller");
        categories.add("Tele-Caller");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CURRENT_ACTIVITY, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        designation.setAdapter(dataAdapter);


        // Spinner click listener
        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                designation.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        // Spinner Drop down elements
        List<String> location = new ArrayList<String>();
        location.add("RSpuram-CBE");
        location.add("Hopes-CBE");
        location.add("Pollachi");
        location.add("Salem");
        location.add("Erode");
        location.add("Chennai");

        // Creating adapter for spinner
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(CURRENT_ACTIVITY, android.R.layout.simple_spinner_dropdown_item, location);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        location_spinner.setAdapter(locationAdapter);


        // Spinner click listener
        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                location_spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void Initialize() {
        designation = (Spinner) findViewById(R.id.designation);
        location_spinner = (Spinner) findViewById(R.id.location);
        signin=(TextView)findViewById(R.id.signup_text);
    }


}
