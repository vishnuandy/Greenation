package com.protagonist.greennation.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Santhosh R on 18-12-2016.
 */

public class LeaderStatus implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LeaderStatus> CREATOR = new Parcelable.Creator<LeaderStatus>() {
        @Override
        public LeaderStatus createFromParcel(Parcel in) {
            return new LeaderStatus(in);
        }

        @Override
        public LeaderStatus[] newArray(int size) {
            return new LeaderStatus[size];
        }
    };
    private String id;
    private String leaderboard_level_name;
    private String message;

    protected LeaderStatus(Parcel in) {
        id = in.readString();
        message = in.readString();
        leaderboard_level_name = in.readString();
    }

    public static Creator<LeaderStatus> getCREATOR() {
        return CREATOR;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeaderboard_level_name() {
        return leaderboard_level_name;
    }

    public void setLeaderboard_level_name(String leaderboard_level_name) {
        this.leaderboard_level_name = leaderboard_level_name;
    }

    @Override
    public String toString() {
        return "LeaderStatus [id = " + id + ", leaderboard_level_name = " + leaderboard_level_name + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(leaderboard_level_name);
        dest.writeString(message);
    }
}