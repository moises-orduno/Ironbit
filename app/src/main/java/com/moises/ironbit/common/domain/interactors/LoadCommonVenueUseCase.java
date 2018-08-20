package com.moises.ironbit.common.domain.interactors;


import com.moises.ironbit.common.domain.model.VenueRepository;
import com.moises.ironbit.common.model.FoursquareRequest;
import com.moises.ironbit.common.model.FoursquareResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoadCommonVenueUseCase implements LoadVenueUseCase {
    private final VenueRepository greetingRepository;

    @Inject
    public LoadCommonVenueUseCase(VenueRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Single<FoursquareResponse> execute(FoursquareRequest foursquareRequest) {
        return greetingRepository.getGreeting(foursquareRequest);
    }
}
