package com.example.mvpsample.downloadmvp;

import android.os.Handler;
import android.os.Message;

import com.example.mvpsample.downloadmvp.common.Constants;
import com.example.mvpsample.downloadmvp.common.DownloadCallback;
import com.example.mvpsample.downloadmvp.common.HttpUtil;

public class DownloadModel implements IDownloadModel {
    private IDowndownPresenter mIDowndownPresenter;

    public DownloadModel(IDowndownPresenter iDowndownPresenter) {
        this.mIDowndownPresenter = iDowndownPresenter;
    }

    @Override
    public void download(String url) {
        HttpUtil.HttpGet(url, new DownloadCallback(handler));
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 300:
                    int percent = msg.arg1;
                    if (percent < 100) {
                        mIDowndownPresenter.downloadProgress(percent);
                    } else {
                        mIDowndownPresenter.downloadSuccess(Constants.LOCAL_FILE_PATH);
                    }
                    break;
                case 404:
                    mIDowndownPresenter.downloadFail();
                    break;
            }
        }
    };
}
