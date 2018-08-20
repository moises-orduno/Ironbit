package com.moises.ironbit.lobby.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.moises.ironbit.common.domain.interactors.LoadCommonVenueUseCase;
import com.moises.ironbit.common.domain.interactors.LoadVenueUseCase;
import com.moises.ironbit.common.model.FoursquareRequest;
import com.moises.ironbit.common.viewmodel.Response;
import com.moises.ironbit.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

public class VenueViewModel extends ViewModel {

    private LoadCommonVenueUseCase loadCommonGreetingUseCase;

    private SchedulersFacade schedulersFacade;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    public VenueViewModel() {
    }

    public VenueViewModel(LoadCommonVenueUseCase loadCommonGreetingUseCase,
                          SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public void loadVenues(double lat, double lon) {
        loadVenues(loadCommonGreetingUseCase,lat,lon);
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    private void loadVenues(LoadVenueUseCase loadVenueUseCase, double lat, double lon) {
        disposables.add(loadVenueUseCase.execute(new FoursquareRequest(lat,lon))
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(Response.loading()))
                .subscribe(
                        greeting -> response.setValue(Response.success(greeting)),
                        throwable -> response.setValue(Response.error(throwable))
                )
        );
    }
}
