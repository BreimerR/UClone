package com.alc.uclone.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Car implements Parcelable {

    private String uid;
    private String currentState;
    private String rating;
    private String lat;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public boolean isFree() {
        return !currentState.equals("") && currentState.equals("true");
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    private String lng;
    private String carstate;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LatLng getLocation() {
        // parse the strings to a location
        return new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCarstate() {
        return carstate;
    }

    public void setCarstate(String carstate) {
        this.carstate = carstate;
    }

    public Car(String uid, String currentState, String rating, String lat, String lng) {
        this.uid  = uid;
        this.currentState = currentState;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
    }


    private Car(Parcel in) {
        uid = in.readString();
        currentState = in.readString();
        rating = in.readString();
        carstate = in.readString();
        lat = in.readString();
        lng = in.readString();

    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(currentState);
        dest.writeString(rating);
        dest.writeString(carstate);
        dest.writeString(lat);
        dest.writeString(lng);
    }

}
