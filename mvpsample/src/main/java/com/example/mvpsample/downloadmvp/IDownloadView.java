package com.example.mvpsample.downloadmvp;

public interface IDownloadView {

    /**
     * 显示进度条
     *
     * @param show
     */
    void showProgressBar(boolean show);

    /**
     * 设置下载的进度值
     *
     * @param progress
     */
    void setProcessProgress(int progress);

    /**
     * 根据数据设置相关的view
     *
     * @param result
     */
    void setImgView(String result);

    /**
     * 显示获取数据的错误信息
     */
    void showFailToast();
}
