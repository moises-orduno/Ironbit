package com.moises.ironbit.common.domain.interactors;


import com.moises.ironbit.common.domain.model.VenueRepository;
import com.moises.ironbit.common.model.venue.FoursquareVenueRequest;
import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venues.FoursquareRequest;
import com.moises.ironbit.common.model.venues.FoursquareResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoadCommonVenueUseCase implements LoadVenueUseCase {
    private final VenueRepository mRepository;

    @Inject
    public LoadCommonVenueUseCase(VenueRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<FoursquareResponse> execute(FoursquareRequest foursquareRequest) {
        return mRepository.getVenues(foursquareRequest);
    }

    @Override
    public Single<FoursquareVenueResponse> executeById(FoursquareVenueRequest foursquareRequest) {
        return mRepository.getVenueById(foursquareRequest);
    }
}
