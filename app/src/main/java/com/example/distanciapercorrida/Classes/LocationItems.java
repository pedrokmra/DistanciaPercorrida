package com.example.distanciapercorrida.Classes;

import java.util.Date;

public class LocationItems {
    String userId, latitude, longitude, distance, date;

    public LocationItems() {
    }

    public LocationItems(String userId, String latitude, String longitude, String distance, String date) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
