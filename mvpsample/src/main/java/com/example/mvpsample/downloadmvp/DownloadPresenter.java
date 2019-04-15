package com.example.mvpsample.downloadmvp;

public class DownloadPresenter implements IDowndownPresenter {
    private IDownloadView mIDownloadView;
    private IDownloadModel mIDownloadModel;

    public DownloadPresenter(IDownloadView iDownloadView) {
        mIDownloadView = iDownloadView;
        mIDownloadModel = new DownloadModel(this);
    }

    /**
     * P层向M层发起下载图片的请求操作
     *
     * @param url
     */
    @Override
    public void download(String url) {
        mIDownloadView.showProgressBar(true);
        mIDownloadModel.download(url);
    }

    /**
     * 获取到M层的数据 并把得到的值传递给V层
     *
     * @param result
     */
    @Override
    public void downloadSuccess(String result) {
        mIDownloadView.showProgressBar(false);
        mIDownloadView.setImgView(result);
    }

    /**
     * 获取数据的下载进度值
     *
     * @param progress 当前进度值
     */
    @Override
    public void downloadProgress(int progress) {
        mIDownloadView.setProcessProgress(progress);
    }

    /**
     * 请求网路数据过程中出现的失败的操作
     */
    @Override
    public void downloadFail() {
        mIDownloadView.showProgressBar(false);
        mIDownloadView.showFailToast();
    }
}
