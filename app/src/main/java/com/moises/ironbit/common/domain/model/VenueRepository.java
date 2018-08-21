package com.moises.ironbit.common.domain.model;


import com.moises.ironbit.common.api.FoursquareService;
import com.moises.ironbit.common.model.venue.FoursquareVenueRequest;
import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venues.FoursquareRequest;
import com.moises.ironbit.common.model.venues.FoursquareResponse;

import io.reactivex.Single;

public class VenueRepository {

    public Single<FoursquareResponse> getVenues(FoursquareRequest foursquareRequest) {
        return FoursquareService.getInstance().venues(foursquareRequest);
    }

    public Single<FoursquareVenueResponse> getVenueById(FoursquareVenueRequest foursquareRequest) {
        return FoursquareService.getInstance().venueById(foursquareRequest);
    }
}
