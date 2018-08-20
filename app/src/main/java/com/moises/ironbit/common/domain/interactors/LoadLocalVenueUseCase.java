package com.moises.ironbit.common.domain.interactors;


import com.moises.ironbit.common.dao.LocalVenue;

import java.util.List;

import io.reactivex.Single;

public interface LoadLocalVenueUseCase {
    Single<List<LocalVenue>> execute();

    Single<Boolean> executeInsert(LocalVenue localVenue);

    Single<Boolean> executeDelete(LocalVenue localVenue);

}
