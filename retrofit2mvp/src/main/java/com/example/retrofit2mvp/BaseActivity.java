package com.example.retrofit2mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.retrofit2mvp.mvp.BaseModel;
import com.example.retrofit2mvp.mvp.BasePresenter;
import com.example.retrofit2mvp.mvp.MvpView;

import static com.example.retrofit2mvp.http.base.BaseObserver.NETWORK_ERROR;

/**
 * Created by ${jz} on 2018/10/23。
 * activity基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    protected P mPresenter;

    protected abstract P createPresenter();

    //错误提示框  警告框  成功提示框 加载进度框 （只是提供个案例 可自定义）
    //private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        setStatusBar();

        this.initToolbar(savedInstanceState);
        this.initData();
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 处理顶部title
     *
     * @param savedInstanceState
     */
    protected abstract void initToolbar(Bundle savedInstanceState);

    /**
     * 数据初始化操作
     */
    protected abstract void initData();

    /**
     * 此处设置沉浸式地方
     */
    protected void setStatusBar() {
        //StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
    }

    /**
     * 封装toast方法（自行去实现）
     *
     * @param str
     */
    public void showToast(String str) {
    }

    public void showLongToast(String str) {
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    /**
     * 返回所有状态  除去指定的值  可设置所有（根据需求）
     *
     * @param model
     */
    @Override
    public void onErrorCode(BaseModel model) {
        if (model.getCode() == NETWORK_ERROR) {

        }
    }

    //显示加载进度框回调
    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    //隐藏进度框回调
    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }

    /**
     * 进度款消失
     */
    public void closeLoadingDialog() {
        Toast.makeText(mContext, "加载成功！", Toast.LENGTH_SHORT).show();
//        if (promptDialog != null) {
//            promptDialog.dismiss();
//        }
    }

    /**
     * 加载中...
     */
    public void showLoadingDialog() {
        Toast.makeText(mContext, "加载中。。。", Toast.LENGTH_SHORT).show();
//        if (promptDialog == null) {
//            promptDialog = new PromptDialog(this);
//        }
//        promptDialog.showLoading("加载中...", false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}

