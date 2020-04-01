package com.example.mvpsample.downloadmvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvpsample.R;
import com.example.mvpsample.downloadmvp.common.Constants;

/**
 * 使用MVP的方式实现下载图片到本地的操作
 * <p>
 * 同时使用okhttp的方式进行请求  Glide加载图片
 */
public class Main2Activity extends AppCompatActivity implements IDownloadView {
    private static final String TAG = "Main2Activity";

    private ImageView iv;
    private Button btSuccess, btErr;
    private Context mContext;
    private DownloadPresenter downloadPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main2);
        initControls();

        initDownloadData();
    }

    private void initDownloadData() {
        downloadPresenter = new DownloadPresenter(this);

        btSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPresenter.download(Constants.DOWNLOAD_URL);
            }
        });

        btErr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPresenter.download(Constants.DOWNLOAD_ERROR_URL);
            }
        });

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("下载图片");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
    }

    /**
     * 初始化布局的控件
     */
    private void initControls() {
        iv = (ImageView) findViewById(R.id.iv);
        btSuccess = (Button) findViewById(R.id.bt_success);
        btErr = (Button) findViewById(R.id.bt_err);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setProcessProgress(int progress) {
        progressDialog.setProgress(progress);
    }

    @Override
    public void setImgView(String result) {
        Glide.with(mContext).load(result).into(iv);
        Log.e(TAG, "setImgView: " + result);
    }

    @Override
    public void showFailToast() {
        Toast.makeText(mContext, "下载失败或者异常", Toast.LENGTH_SHORT).show();
    }
}
