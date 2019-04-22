package com.sample.yl.sampledemo.bottomtabbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.yl.sampledemo.R;

/**
 * Created by ${jz} on 2017/9/18.
 */

public class TabFragment2 extends Fragment {

    public TabFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        RelativeLayout bag = (RelativeLayout) layout.findViewById(R.id.rl_bag);
        bag.setBackgroundColor(getResources().getColor(R.color.colorYellow));

        TextView tv = (TextView) layout.findViewById(R.id.tv_table);
        tv.setText("tab  2");

        return layout;
    }

}