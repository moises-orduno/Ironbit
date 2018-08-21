package com.moises.ironbit.common.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.moises.ironbit.common.model.venue.FoursquareVenueRequest;
import com.moises.ironbit.common.model.venue.FoursquareVenueResponse;
import com.moises.ironbit.common.model.venues.FoursquareRequest;
import com.moises.ironbit.common.model.venues.FoursquareResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by mou on 18/04/18.
 */

public class FoursquareService {

    private static volatile FoursquareService mFoursquareService;
    private final FoursquareApi mFoursquareApi;
    private static final String URL="https://api.foursquare.com/";
    private final String CLIENT_ID="M5RBAWKHD3GAOPD5XJDR3KVXS1MFBMT3IGEXTW4CE2SDIOVN";
    private final String CLIENT_SECRET="RF0ITYBT2WNI3YN3UVWTWVH0BTVK52SG4HEP0LY5MSNYDQEP";
    private final String API_VERSION="20180323";
    private final String LIMIT="10";
    private final String LATLON="19.442204,-99.168943";
    private final String QUERY="coffee";

    //OkHttp
    public static final int CONNECT_TIMEOUT = 60;

    private FoursquareService() {

        mFoursquareApi = new Retrofit.Builder()
                .client(getClient())
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FoursquareApi.class);
    }

    private static OkHttpClient getClient() {
        OkHttpClient httpClient;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(new HeadersInterceptor(service))
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return httpClient;
    }


    public static FoursquareService getInstance() {
        if(mFoursquareService == null) {
            synchronized (FoursquareService.class) {
                if(mFoursquareService == null) {
                    mFoursquareService = new FoursquareService();
                }
            }
        }
        return mFoursquareService;
    }

    public Single<FoursquareVenueResponse> venueById(FoursquareVenueRequest foursquareRequest) {
        return mFoursquareApi.venueById(foursquareRequest.getId(),CLIENT_ID,CLIENT_SECRET,API_VERSION);
    }

    public Single<FoursquareResponse> venues(FoursquareRequest foursquareRequest) {
        return mFoursquareApi.venues(CLIENT_ID,CLIENT_SECRET,API_VERSION,LIMIT,foursquareRequest.getLatLon(),QUERY);
    }

}
