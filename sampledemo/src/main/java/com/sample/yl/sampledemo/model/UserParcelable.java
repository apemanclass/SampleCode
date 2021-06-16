package com.sample.yl.sampledemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by  on 6/16/21。
 */
public class UserParcelable implements Parcelable {
    String name;

    public UserParcelable(String name) {
        this.name = name;
    }

    protected UserParcelable(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };

    @Override
    public String toString() {
        return "UserParcelable{" +
                "name='" + name + '\'' +
                '}';
    }
}
