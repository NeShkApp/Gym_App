package com.example.gym_app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Plan implements Parcelable {
    private Training training;
    private int minutes;
    private String day;
    private boolean isAccomplished;


    public Plan(Training training, int minutes, String day, boolean isAccomplished) {
        this.training = training;
        this.minutes = minutes;
        this.day = day;
        this.isAccomplished = isAccomplished;
    }

    protected Plan(Parcel in) {
        training = in.readParcelable(Training.class.getClassLoader());
        minutes = in.readInt();
        day = in.readString();
        isAccomplished = in.readByte() != 0;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }

    public Training getTraining() {
        return training;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getDay() {
        return day;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "training=" + training +
                ", minutes=" + minutes +
                ", day='" + day + '\'' +
                ", isAccomplished=" + isAccomplished +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(training, i);
        parcel.writeInt(minutes);
        parcel.writeString(day);
        parcel.writeByte((byte) (isAccomplished ? 1 : 0));
    }
}
