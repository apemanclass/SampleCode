package com.bsyx.schedule;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ${jz} on 2019/9/5。
 */
public class SharePreferenceUtil {

    public final String NAME = "data";

    private static SharePreferenceUtil sharePreferenceUtils;

    private SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public static SharePreferenceUtil getInstance(Context context) {
        if (sharePreferenceUtils == null) {
            sharePreferenceUtils = new SharePreferenceUtil(context);
        }
        return sharePreferenceUtils;
    }

    private String URL_IP = "url";

    public void setURL(String url) {
        editor.putString(URL_IP, url);
        editor.commit();
    }

    public String getURL() {
        return mSharedPreferences.getString(URL_IP, null);
    }

}
