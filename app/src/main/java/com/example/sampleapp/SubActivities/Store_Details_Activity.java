package com.example.sampleapp.SubActivities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Store_Details_Activity extends AppCompatActivity implements OnChartValueSelectedListener {
private int companya,companyb,companyc,companyd;
private TextView toolbar_title;
private RelativeLayout back;
private Activity CURRENT_ACTIVITY = Store_Details_Activity.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_details);

        Initialize();
        createChart();

        toolbar_title.setText("Branch - A");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CURRENT_ACTIVITY.finish();
            }
        });
    }

    private void Initialize() {
        toolbar_title=(TextView)findViewById(R.id.toolbar_title);
        back=(RelativeLayout)findViewById(R.id.back);
    }

    private void createChart() {

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getLegend().setEnabled(false);
        pieChart.setRotationAngle(45);

        companya=50;
        companyb=20;
        companyc=15;
        companyd=15;

//        // IMPORTANT: In admin_layout PieChart, no values (Entry) should have the same
//         xIndex (even if from different DataSets), since no values can be
//         drawn above each other.
        Float per = Float.valueOf(100);
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(companya, 0));
        yvalues.add(new Entry(companyb, 1));
        yvalues.add(new Entry(companyc, 2));
        yvalues.add(new Entry(companyd, 3));


        PieDataSet dataSet = new PieDataSet(yvalues, "");

        dataSet.setHighlightEnabled(false);
        dataSet.setDrawValues(false);

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("");
        xVals.add("");
        xVals.add("");
        xVals.add("");

        PieData data = new PieData(xVals, dataSet);
        data.setHighlightEnabled(false);
        data.setDrawValues(false);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.setDescription("");
        pieChart.setDrawMarkerViews(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setDrawSlicesUnderHole(false);
        pieChart.setUsePercentValues(false);

        final int[] MY_COLORS = {Color.parseColor("#e94a35"), Color.parseColor("#4dd286"), Color.parseColor("#fbce40"), Color.parseColor("#cdcdcd")};

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : MY_COLORS) colors.add(c);

        dataSet.setColors(colors);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(this);

        pieChart.animateXY(1400, 1400);


    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
