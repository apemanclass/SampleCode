package com.sample.yl.sampledemo.loadingdrawable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sample.yl.sampledemo.R;

import app.dinus.com.loadingdrawable.LoadingView;

public class DialogLoadingActivity extends AppCompatActivity {
    private LoadingView loading;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DialogLoadingActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_loading);

        Button bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogLoading(DialogLoadingActivity.this).show();
            }
        });
    }
}
