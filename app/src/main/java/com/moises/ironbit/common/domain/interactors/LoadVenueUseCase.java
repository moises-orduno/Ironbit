package com.moises.ironbit.common.domain.interactors;


import com.moises.ironbit.common.model.FoursquareRequest;
import com.moises.ironbit.common.model.FoursquareResponse;

import io.reactivex.Single;

public interface LoadVenueUseCase {
    Single<FoursquareResponse> execute(FoursquareRequest foursquareResponse);
}
