package com.sample.yl.sampledemo.dragbubbleview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.sample.yl.mylibrary.custombubble.DragBubbleView;
import com.sample.yl.sampledemo.R;

/**
 * 使用消息汽包提示的方法
 */
public class BubbleViewActivity extends AppCompatActivity implements DragBubbleView.OnBubbleStateListener {

    private DragBubbleView mDragBubbleView;
    private Button bt_bubble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_view);

        initControls();
        setBubble();
    }


    /**
     * 初始化布局的控件
     */
    private void initControls() {
        mDragBubbleView = (DragBubbleView) findViewById(R.id.drag_bubble);
        bt_bubble = (Button) findViewById(R.id.bt_bubble);
    }

    private void setBubble() {
        mDragBubbleView.setText("99+");
        mDragBubbleView.setOnBubbleStateListener(this);

        bt_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDragBubbleView.reCreate();
                mDragBubbleView.setDismiss();
                //Toast.makeText(BubbleViewActivity.this, "从新生成气泡成功！", Toast.LENGTH_SHORT).show();
                ToastUtils.show("从新生成气泡成功！");
            }
        });
    }

    @Override
    public void onDrag() {
        ToastUtils.show("拖拽气泡！");
    }

    @Override
    public void onMove() {
        ToastUtils.show("移动气泡！");
    }

    @Override
    public void onRestore() {
        ToastUtils.show("气泡恢复原来位置！");
    }

    @Override
    public void onDismiss() {
        ToastUtils.show("气泡消失！");
    }
}
