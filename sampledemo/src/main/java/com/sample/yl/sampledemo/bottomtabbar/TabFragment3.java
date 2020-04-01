package com.sample.yl.sampledemo.bottomtabbar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.yl.sampledemo.R;

/**
 * Created by ${jz} on 2017/9/18.
 */

public class TabFragment3 extends Fragment {

    public TabFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        RelativeLayout bag = (RelativeLayout) layout.findViewById(R.id.rl_bag);
        bag.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        TextView tv = (TextView) layout.findViewById(R.id.tv_table);
        tv.setText("tab  3");

        return layout;
    }

}
