package com.moises.ironbit.common.domain.interactors;


import com.moises.ironbit.common.model.venue.FoursquareVenueRequest;
import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venues.FoursquareRequest;
import com.moises.ironbit.common.model.venues.FoursquareResponse;

import io.reactivex.Single;

public interface LoadVenueUseCase {
    Single<FoursquareResponse> execute(FoursquareRequest foursquareResponse);

    Single<FoursquareVenueResponse> executeById(FoursquareVenueRequest foursquareRequest) ;
}
