package com.sample.yl.sampledemo.loadingdrawable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.sample.yl.sampledemo.R;

public class AnimalActivity extends AppCompatActivity {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AnimalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
    }
}
