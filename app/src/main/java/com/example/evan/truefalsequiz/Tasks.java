package com.example.evan.truefalsequiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by per6 on 11/3/17.
 */

public class Tasks implements Parcelable {
    private String name;
    private String description;
    private String date;
    private String availability;
    private boolean star;
    private boolean habit;

    @Override
    public String toString() {
        return name;
    }

    public Tasks(String name, String description, String date, String availability) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.availability = availability;
        this.star = false;
        this.habit = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public boolean isHabit() {
        return habit;
    }

    public void setHabit(boolean habit) {
        this.habit = habit;
    }

    protected Tasks(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readString();
        availability = in.readString();
        star = in.readByte() != 0x00;
        habit = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(availability);
        dest.writeByte((byte) (star ? 0x01 : 0x00));
        dest.writeByte((byte) (habit ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Tasks> CREATOR = new Parcelable.Creator<Tasks>() {
        @Override
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        @Override
        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };
}