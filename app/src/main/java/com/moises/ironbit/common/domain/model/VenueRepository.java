package com.moises.ironbit.common.domain.model;


import com.moises.ironbit.common.api.FoursquareService;
import com.moises.ironbit.common.model.FoursquareRequest;
import com.moises.ironbit.common.model.FoursquareResponse;

import io.reactivex.Single;

public class VenueRepository {

    public Single<FoursquareResponse> getGreeting(FoursquareRequest foursquareRequest) {
        return FoursquareService.getInstance().venues(foursquareRequest);
    }
}
