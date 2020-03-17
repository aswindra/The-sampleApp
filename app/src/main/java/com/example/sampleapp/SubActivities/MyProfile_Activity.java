package com.example.sampleapp.SubActivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.sampleapp.R;

import java.lang.ref.WeakReference;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class MyProfile_Activity extends AppCompatActivity {
    private RelativeLayout Back;
    Activity CURRENT_ACTIVITY = MyProfile_Activity.this;
    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_VIEW = "type_view";
    private SkeletonScreen skeletonScreen;
    View rootView;
    public ShimmerLayout shimmer;

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, MyProfile_Activity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }


    public static class MyHandler extends android.os.Handler {
        private final WeakReference<MyProfile_Activity> activityWeakReference;

        MyHandler(MyProfile_Activity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activityWeakReference.get() != null) {
                activityWeakReference.get().skeletonScreen.hide();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);
        Initialize();
        String mType = getIntent().getStringExtra(PARAMS_TYPE);

        if (TYPE_VIEW.equals(mType)) {
            skeletonScreen = Skeleton.bind(rootView)
                    .load(R.layout.my_profile_activity_skeleton)
                    .duration(2000)
                    .color(R.color.shimmer_color)
                    .angle(0)
                    .show();
        }
        MyHandler myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(1, 3000);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CURRENT_ACTIVITY.finish();
            }
        });
    }

    private void Initialize() {
        Back=(RelativeLayout)findViewById(R.id.back);
        rootView = findViewById(R.id.rootView);
    }
}
