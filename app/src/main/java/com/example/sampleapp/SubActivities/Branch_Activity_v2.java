package com.example.sampleapp.SubActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapp.Adapter.Branch_Adapter;
import com.example.sampleapp.R;
import com.example.sampleapp.Utils.Swipeto_delete_Callback;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class Branch_Activity_v2 extends AppCompatActivity {
    private RelativeLayout Back, store_details;
    RecyclerView recyclerView;
    private TextView title;
    Branch_Adapter branch_adapter;
    RelativeLayout relativelayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branch_layout);

        Initialize();
        title.setText("The Sample App");

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Branch_Activity_v2.this.finish();
            }
        });
        populateRecyclerView();
//        enableSwipeToDeleteAndUndo();
        
        store_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Branch_Activity_v2.this, Store_Details_Activity.class));
            }
        });

    }

    private void populateRecyclerView() {

        ArrayList<HashMap<String,String>> dummy_datas=new ArrayList<>();
        for (int i=0;i<10;i++){
            HashMap<String,String>datas=new HashMap<>();
            datas.put("date","01/01/2020");
            datas.put("name","Aswin");
            datas.put("branch","Branch_A");
            dummy_datas.add(datas);
        }

        branch_adapter = new Branch_Adapter(dummy_datas);
        recyclerView.setAdapter(branch_adapter);
    }

//    private void enableSwipeToDeleteAndUndo() {
//        Swipeto_delete_Callback swipeToDeleteCallback = new Swipeto_delete_Callback(this) {
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//
//                final int position = viewHolder.getAdapterPosition();
//                final String item = branch_adapter.getData().get(position);
//
//                branch_adapter.removeItem(position);
//
//
//                Snackbar snackbar = Snackbar
//                        .make(relativelayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        branch_adapter.restoreItem(item, position);
//                        recyclerView.scrollToPosition(position);
//                    }
//                });
//
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();
//
//            }
//        };
//
//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
//        itemTouchhelper.attachToRecyclerView(recyclerView);
//    }

    private void Initialize() {
        Back = (RelativeLayout) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.toolbar_title);
        recyclerView = findViewById(R.id.recycler);
        relativelayout = findViewById(R.id.relativelayout);
        store_details = (RelativeLayout) findViewById(R.id.store_details);
    }
}
