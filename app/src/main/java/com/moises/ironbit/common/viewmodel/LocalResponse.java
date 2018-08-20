package com.moises.ironbit.common.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.moises.ironbit.common.dao.LocalVenue;

import java.util.List;

import static com.moises.ironbit.common.viewmodel.Status.ERROR;
import static com.moises.ironbit.common.viewmodel.Status.LOADING;
import static com.moises.ironbit.common.viewmodel.Status.SUCCESS_DELETE;
import static com.moises.ironbit.common.viewmodel.Status.SUCCESS_INSERT;
import static com.moises.ironbit.common.viewmodel.Status.SUCCESS_LOAD;


public class LocalResponse {

    public final Status status;

    @Nullable
    public final List<LocalVenue> data;

    @Nullable
    public final Throwable error;

    private LocalResponse(Status status, @Nullable List<LocalVenue> data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static LocalResponse loading() {
        return new LocalResponse(LOADING, null, null);
    }


    public static LocalResponse successInsert(@NonNull Boolean data) {
        return new LocalResponse(SUCCESS_INSERT, null, null);
    }

    public static LocalResponse successDelete(@NonNull Boolean data) {
        return new LocalResponse(SUCCESS_DELETE, null, null);
    }


    public static LocalResponse successLoad(@NonNull List<LocalVenue> data) {
        return new LocalResponse(SUCCESS_LOAD, data, null);
    }


    public static LocalResponse error(@NonNull Throwable error) {
        return new LocalResponse(ERROR, null, error);
    }
}
