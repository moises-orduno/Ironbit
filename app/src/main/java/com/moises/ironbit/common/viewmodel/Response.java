package com.moises.ironbit.common.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.model.FoursquareResponse;

import java.util.List;

import static com.moises.ironbit.common.viewmodel.Status.ERROR;
import static com.moises.ironbit.common.viewmodel.Status.LOADING;
import static com.moises.ironbit.common.viewmodel.Status.SUCCESS;


public class Response {

    public final Status status;

    @Nullable
    public final FoursquareResponse data;

    @Nullable
    public final Throwable error;

    private Response(Status status, @Nullable FoursquareResponse data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static Response loading() {
        return new Response(LOADING, null, null);
    }

    public static Response success(@NonNull FoursquareResponse data) {
        return new Response(SUCCESS, data, null);
    }

    public static Response success(@NonNull Boolean data) {
        return new Response(SUCCESS, null, null);
    }

    public static Response success(@NonNull List<LocalVenue> data) {
        return new Response(SUCCESS, null, null);
    }


    public static Response error(@NonNull Throwable error) {
        return new Response(ERROR, null, error);
    }
}
