package com.sample.yl.sampledemo.loadingdrawable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sample.yl.sampledemo.R;

import app.dinus.com.loadingdrawable.LoadingView;

public class CircleRotateActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CircleRotateActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_rotate);

        final LoadingView gear_view = (LoadingView) findViewById(R.id.gear_view);

        Button bt1 = findViewById(R.id.bt1);
        Button bt2 = findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gear_view.setVisibility(View.VISIBLE);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gear_view.setVisibility(View.INVISIBLE);
            }
        });

    }
}
