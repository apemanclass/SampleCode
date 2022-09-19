package com.sample.yl.sampledemo.meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.sample.yl.sampledemo.R;

public class VideoMeetingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mItem1, mItem2, mItem3, mItem4, mItem5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_meeting);
        mItem1 = findViewById(R.id.item_1);
        mItem2 = findViewById(R.id.item_2);
        mItem3 = findViewById(R.id.item_3);
        mItem4 = findViewById(R.id.item_4);
        mItem5 = findViewById(R.id.item_5);
        mItem1.setOnClickListener(this);
        mItem2.setOnClickListener(this);
        mItem3.setOnClickListener(this);
        mItem4.setOnClickListener(this);
        mItem5.setOnClickListener(this);
    }

    public void onClick(View view) {
        mItem3.setText("3");
        mItem4.setText("4");
        if (view == mItem1) {
            setSpec(0, 0, mItem1, 2, 1);
            setSpec(0, 1, mItem2, 1, 1);
            setSpec(1, 1, mItem3, 1, 1);
            setSpec(0, 2, mItem4, 1, 1);
            setSpec(1, 2, mItem5, 1, 1);
        } else if (view == mItem2) {
            setSpec(0, 0, mItem1, 1, 1);
            setSpec(1, 0, mItem2, 1, 2);
            setSpec(0, 1, mItem3, 1, 1);
            setSpec(0, 2, mItem4, 1, 1);
            setSpec(1, 2, mItem5, 1, 1);
        } else if (view == mItem3) {
            setSpec(0, 0, mItem1, 1, 1);
            setSpec(1, 0, mItem2, 1, 1);
            setSpec(0, 1, mItem3, 2, 1);
            setSpec(0, 2, mItem4, 1, 1);
            setSpec(1, 2, mItem5, 1, 1);
        } else if (view == mItem4) {
            setSpec(0, 0, mItem1, 1, 1);
            setSpec(1, 0, mItem2, 1, 1);
            setSpec(1, 1, mItem3, 1, 1);
            setSpec(0, 1, mItem4, 1, 2);
            setSpec(1, 2, mItem5, 1, 1);
        } else if (view == mItem5) {
            setSpec(0, 0, mItem1, 1, 1);
            setSpec(1, 0, mItem2, 1, 1);
            setSpec(0, 1, mItem3, 1, 1);
            setSpec(1, 1, mItem4, 1, 1);
            setSpec(0, 2, mItem5, 2, 1);
        }
    }


    /**
     * @param columnIndex  起始横向位置
     * @param rowIndex     起始纵向位置
     * @param view         VIew对象
     * @param columnSpec   横向占几格
     * @param rowSpec      纵向占几格
     */
    private void setSpec(int columnIndex, int rowIndex, View view, int columnSpec, int rowSpec) {
        GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) view.getLayoutParams();
        layoutParams.columnSpec = GridLayout.spec(columnIndex, columnSpec, GridLayout.FILL, 1);
        layoutParams.rowSpec = GridLayout.spec(rowIndex, rowSpec, GridLayout.FILL, 1);
        view.setLayoutParams(layoutParams);
    }
}