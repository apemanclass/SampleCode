package com.sample.yl.sampledemo.rxjava2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by ${jz} on 2018/12/1。
 * 使用URL读取服务器的txt文件，并结合rxjava读取文件流的数据，并显示数据
 */
public class RxJava1ReadTxt {

//String strUrl = "http://192.168.0.218/myproject/resource/20181031/df51d30f-751d-4540-ae65-6fb2acad9e40.txt";

//    Observable.create(new Observable.OnSubscribe<String>() {
//        @Override
//        public void call(Subscriber<? super String> subscriber) {
//            可以创建url实例
//            URL url = null;
//            StringBuffer buffer = null;
//            try {
//                url = new URL(strUrl);
//                buffer = new StringBuffer();
//                //字节输入流
//                InputStream is = url.openStream();
//                //字节流转字符流
//                InputStreamReader isr = new InputStreamReader(is, "gbk");
//                //再转缓冲流  提高读取效率
//                BufferedReader br = new BufferedReader(isr);
//                String s = "";
//                while ((s = br.readLine()) != null) {
//                    buffer.append(s);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            subscriber.onNext(buffer.toString());
//        }
//    })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<String>() {
//        @Override
//        public void onCompleted() {
//
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onNext(String s) {
//            getMvpView().showBubbleTxt(s);
//        }
//    });
}
