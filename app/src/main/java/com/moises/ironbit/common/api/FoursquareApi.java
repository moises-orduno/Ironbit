package com.moises.ironbit.common.api;


import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venues.FoursquareResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mou on 18/04/18.
 */

public interface FoursquareApi {


    @GET("v2/venues/explore")
    Single<FoursquareResponse> venues(@Query("client_id") String clientId, @Query("client_secret") String clientSecret,
                                      @Query("v") String version, @Query("limit") String limit,
                                      @Query("ll") String latlon, @Query("query") String query);

    @GET("v2/venues/{id}")
    Single<FoursquareVenueResponse> venueById(@Path("id") String id, @Query("client_id") String clientId,
                                              @Query("client_secret") String clientSecret,
                                              @Query("v") String version);


}
