package com.moises.ironbit.lobby.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.domain.interactors.LoadCommonLocalVenueUseCase;
import com.moises.ironbit.common.model.Venue;
import com.moises.ironbit.common.viewmodel.LocalResponse;
import com.moises.ironbit.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

public class LocalVenueViewModel extends ViewModel {

    private LoadCommonLocalVenueUseCase loadCommonGreetingUseCase;

    private SchedulersFacade schedulersFacade;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<LocalResponse> response = new MutableLiveData<>();

    public LocalVenueViewModel() {
    }

    public LocalVenueViewModel(LoadCommonLocalVenueUseCase loadCommonGreetingUseCase,
                               SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public void loadLocalVenues() {
        getLocalVenue(loadCommonGreetingUseCase);
    }

    public void insertLocalVenue(Venue venue) {
        insertLocalVenue(loadCommonGreetingUseCase, new LocalVenue(0,venue.getId(),
                venue.getName(),venue.getLocation().getAddress()));

    }


    public MutableLiveData<LocalResponse> response() {
        return response;
    }

    private void insertLocalVenue(LoadCommonLocalVenueUseCase loadCommonLocalVenueCase, LocalVenue localVenue) {
        disposables.add(loadCommonLocalVenueCase.executeInsert(localVenue)
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(LocalResponse.loading()))
                .subscribe(
                        isSuccess -> response.setValue(LocalResponse.successInsert(isSuccess)),
                        throwable -> response.setValue(LocalResponse.error(throwable))
                )
        );
    }

    private void getLocalVenue(LoadCommonLocalVenueUseCase loadCommonLocalVenueCase) {
        disposables.add(loadCommonLocalVenueCase.execute()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(LocalResponse.loading()))
                .subscribe(
                        localVenues -> response.setValue(LocalResponse.successLoad(localVenues)),
                        throwable -> response.setValue(LocalResponse.error(throwable))
                )
        );
    }

    public void deleteLocalVenue(LocalVenue venue) {
        deleteLocalVenue(loadCommonGreetingUseCase, venue);
    }

    private void deleteLocalVenue(LoadCommonLocalVenueUseCase loadCommonLocalVenueCase, LocalVenue localVenue) {
        disposables.add(loadCommonLocalVenueCase.executeDelete(localVenue)
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(LocalResponse.loading()))
                .subscribe(
                        localVenues -> response.setValue(LocalResponse.successDelete(localVenues)),
                        throwable -> response.setValue(LocalResponse.error(throwable))
                )
        );
    }
}
