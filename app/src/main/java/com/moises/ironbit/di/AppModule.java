package com.moises.ironbit.di;

import android.content.Context;


import com.moises.ironbit.App;
import com.moises.ironbit.common.domain.model.VenueRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    VenueRepository provideCommonHelloService() {
        return new VenueRepository();
    }


}
