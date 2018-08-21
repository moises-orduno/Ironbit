package com.moises.ironbit.common.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venues.FoursquareResponse;

import java.util.List;

import static com.moises.ironbit.common.viewmodel.Status.ERROR;
import static com.moises.ironbit.common.viewmodel.Status.LOADING;
import static com.moises.ironbit.common.viewmodel.Status.SUCCESS;


public class Response {

    public final Status status;

    public FoursquareResponse data;

    public FoursquareVenueResponse dataVenue;

    public Throwable error;

    private Response(Status status, FoursquareResponse data) {
        this.status = status;
        this.data = data;
    }

    private Response(Status status, FoursquareVenueResponse data) {
        this.status = status;
        this.dataVenue = data;
    }

    private Response(Status status) {
        this.status = status;
    }

    private Response(Status status, Throwable error) {
        this.status = status;
        this.error = error;
    }


    public static Response loading() {
        return new Response(LOADING);
    }

    public static Response success(@NonNull FoursquareResponse data) {
        return new Response(SUCCESS, data);
    }

    public static Response success(@NonNull FoursquareVenueResponse data) {
        return new Response(SUCCESS, data);
    }

    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, error);
    }
}
