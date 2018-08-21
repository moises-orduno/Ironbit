package com.moises.ironbit.lobby;


import com.moises.ironbit.common.domain.interactors.LoadCommonLocalVenueUseCase;
import com.moises.ironbit.common.domain.interactors.LoadCommonVenueUseCase;
import com.moises.ironbit.common.domain.model.LocalVenueRepository;
import com.moises.ironbit.common.domain.model.VenueRepository;
import com.moises.ironbit.lobby.viewmodel.LocalVenueViewModelFactory;
import com.moises.ironbit.lobby.viewmodel.VenueViewModelFactory;
import com.moises.ironbit.rx.SchedulersFacade;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Define LobbyActivity-specific dependencies here.
 */
@Module
public class LobbyModule {


    @Provides
    VenueViewModelFactory provideLobbyViewModelFactory(LoadCommonVenueUseCase loadCommonVenueUseCase,
                                                       SchedulersFacade schedulersFacade) {
        return new VenueViewModelFactory(loadCommonVenueUseCase, schedulersFacade);
    }

    @Provides
    LocalVenueRepository provideLocalVenueRepository() {
        return new LocalVenueRepository();
    }

    @Provides
    VenueRepository provideCommonHelloService() {
        return new VenueRepository();
    }

    @Provides
    LocalVenueViewModelFactory provideLocalVenueViewModelFactory(LoadCommonLocalVenueUseCase loadCommonVenueUseCase,
                                                                 SchedulersFacade schedulersFacade) {
        return new LocalVenueViewModelFactory(loadCommonVenueUseCase, schedulersFacade);
    }
}
