package com.example.mvpsample.basemvp;

/**
 * Created by jiazhui on 2017/12/7.
 * <p>
 * (1)mMainModel实现IMainModel,实现getData()方法，在getData()方法中获取数据（比如网络请求）
 * <p>
 * (2)如图可知Model层请求到了数据，没有和P层和V层有任何关联。咋办呢？
 * <p>
 * <p>
 * ----      P层------>M层
 * 要想办法把数据传给P层，因为如图 P层和V层是互相交互的，给到了P，V层就会获得数据
 * <p>
 * ----      P层<------>V层   PV互相交互
 * <p>
 * <p>
 * (3)这时候IDownLoadLister接口 就发挥了作用，将M层请求到的数据返还给P层，
 * <p>
 * M层的IDownLoadListener是构造方法 由P层传过去了，保证了单一实例(P层实现了IDownLoadListener接口)
 */

public class mMainModel implements AppContracts.IMainModel {

    private IDownLoadListener<String> iDownLoadListener;



    public mMainModel(IDownLoadListener<String> iDownLoadListener) {
        this.iDownLoadListener = iDownLoadListener;
    }


    @Override
    public void getData() {
        //进行网络操作和一些数据库的操作等

        String str = "M层请求网络成功拿到数据！";

        iDownLoadListener.downloadSuccess(str);
    }

}
