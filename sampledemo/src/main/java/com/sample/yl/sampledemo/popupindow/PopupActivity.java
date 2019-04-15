package com.sample.yl.sampledemo.popupindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.daasuu.bl.ArrowDirection;
import com.daasuu.bl.BubbleLayout;
import com.daasuu.bl.BubblePopupHelper;
import com.sample.yl.sampledemo.R;

import java.util.Random;

/**
 * 演示通用BasePopup使用
 */
public class PopupActivity extends AppCompatActivity {
    private Button button, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BaseDialog dialog = new BaseDialog(PopupActivity.this);
                dialog.setTitle("提 示");
                dialog.setMessage("的撒旦撒旦撒旦撒很屌丝能达到撒谎的三大搜啊的");
                dialog.setNegativeButton("取 消", new BaseDialog.OnCancelOnclickListener() {
                    @Override
                    public void onCancelClick() {
                        dialog.dismiss();
                        Toast.makeText(PopupActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("确 定", new BaseDialog.OnConfirmOnclickListener() {
                    @Override
                    public void onConfirmClick() {
                        dialog.dismiss();
                        Toast.makeText(PopupActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopDialog dialog = new PopDialog(PopupActivity.this);
                dialog.showPopupWindow();
            }
        });


        final BubbleLayout bubbleLayout = (BubbleLayout) LayoutInflater.from(this).inflate(R.layout.layout_sample_popup, null);
        final PopupWindow popupWindow = BubblePopupHelper.create(this, bubbleLayout);
        final Random random = new Random();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] location = new int[2];
                v.getLocationInWindow(location);
                if (random.nextBoolean()) {
                    bubbleLayout.setArrowDirection(ArrowDirection.TOP);
                } else {
                    bubbleLayout.setArrowDirection(ArrowDirection.BOTTOM);
                }
                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], v.getHeight() + location[1]);
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BubbleDialogDemo(PopupActivity.this)
                        .setClickedView(v).show();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuspensionPopWind popWind = new SuspensionPopWind(PopupActivity.this);
                popWind.showPopupWindow();
            }
        });
    }
}
