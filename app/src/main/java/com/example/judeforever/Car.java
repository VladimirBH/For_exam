package com.example.judeforever;

public class Car
{
    private String id;
    private String model;
    private String lon;
    private String lat;

    public Car(String id, String model, String lon, String lat) {
        this.id = id;
        this.model = model;
        this.lon = lon;
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
