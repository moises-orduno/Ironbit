package com.moises.ironbit.lobby.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.moises.ironbit.common.domain.interactors.LoadCommonVenueUseCase;
import com.moises.ironbit.rx.SchedulersFacade;


public class VenueViewModelFactory implements ViewModelProvider.Factory {

    private final LoadCommonVenueUseCase loadCommonGreetingUseCase;


    private final SchedulersFacade schedulersFacade;

    public VenueViewModelFactory(LoadCommonVenueUseCase loadCommonGreetingUseCase,
                                 SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VenueViewModel.class)) {
            return (T) new VenueViewModel(loadCommonGreetingUseCase, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
