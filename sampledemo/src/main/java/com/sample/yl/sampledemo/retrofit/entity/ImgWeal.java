package com.sample.yl.sampledemo.retrofit.entity;

/**
 * Created by ${jz} on 2019/8/23。
 */
public class ImgWeal {

    /**
     * _id : 5cc43919fc3326376038d233
     * createdAt : 2019-04-27T19:12:25.536Z
     * desc : 2019-04-27
     * publishedAt : 2019-04-27T19:12:51.865Z
     * source : web
     * type : 福利
     * url : https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg
     * used : true
     * who : lijinshanmx
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
        return _id == null ? "" : _id;
    }

    public String getCreatedAt() {
        return createdAt == null ? "" : createdAt;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public String getPublishedAt() {
        return publishedAt == null ? "" : publishedAt;
    }

    public String getSource() {
        return source == null ? "" : source;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who == null ? "" : who;
    }
}
