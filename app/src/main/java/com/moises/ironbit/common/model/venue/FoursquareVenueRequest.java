package com.moises.ironbit.common.model.venue;

public class FoursquareVenueRequest {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FoursquareVenueRequest(String id) {

        this.id = id;
    }
}
