package com.example.splashscreen.response_object;

public class DriverLocationResponseObject {
    private int record_id;
    private String driver_id;
    private String map_lat;
    private String map_long;
    private String datetime_added;

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public String getDriverid() {
        return driver_id;
    }

    public void setMap_lat(String map_lat) {
        this.map_lat = map_lat;
    }

    public String getMap_lat() {
        return map_lat;
    }

    public void setMap_long(String map_long) {
        this.map_long = map_long;
    }

    public String getMap_long() {
        return map_long;
    }

    public void setDatetime_added(String datetime_added) {
        this.datetime_added = datetime_added;
    }

    public String getDatetime_added() {
        return datetime_added;
    }

}
