package com.moises.ironbit.common.model.venues;

/**
 * Created by mou on 17/08/18.
 */

public class FoursquareRequest {

    private double lat;
    private double lon;

    public FoursquareRequest(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLatLon() {
        return lat+","+lon;
    }
}
