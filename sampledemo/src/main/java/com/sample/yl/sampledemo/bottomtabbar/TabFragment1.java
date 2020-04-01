package com.sample.yl.sampledemo.bottomtabbar;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.yl.sampledemo.R;

import static com.sample.yl.sampledemo.R.color.colorDef;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {


    public TabFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        RelativeLayout bag = (RelativeLayout) layout.findViewById(R.id.rl_bag);
        bag.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        TextView tv = (TextView) layout.findViewById(R.id.tv_table);
        tv.setText("tab  1");

        return layout;
    }

}
