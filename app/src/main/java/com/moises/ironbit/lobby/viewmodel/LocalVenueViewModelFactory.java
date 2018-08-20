package com.moises.ironbit.lobby.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.moises.ironbit.common.domain.interactors.LoadCommonLocalVenueUseCase;
import com.moises.ironbit.rx.SchedulersFacade;


public class LocalVenueViewModelFactory implements ViewModelProvider.Factory {

    private final LoadCommonLocalVenueUseCase mLoadCommonLocalVenueCase;
    private final SchedulersFacade schedulersFacade;

    public LocalVenueViewModelFactory(LoadCommonLocalVenueUseCase loadCommonLocalVenueCase,
                               SchedulersFacade schedulersFacade) {
        this.mLoadCommonLocalVenueCase = loadCommonLocalVenueCase;
        this.schedulersFacade = schedulersFacade;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LocalVenueViewModel.class)) {
            return (T) new LocalVenueViewModel(mLoadCommonLocalVenueCase, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
