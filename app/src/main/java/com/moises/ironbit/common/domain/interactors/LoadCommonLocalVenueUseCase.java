package com.moises.ironbit.common.domain.interactors;

import android.arch.persistence.room.Room;
import android.content.Context;


import com.moises.ironbit.common.dao.AppDatabase;
import com.moises.ironbit.common.dao.LocalVenue;
import com.moises.ironbit.common.domain.model.LocalVenueRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoadCommonLocalVenueUseCase implements LoadLocalVenueUseCase {
    private final LocalVenueRepository mRepository;
    private final Context mContext;
    private final AppDatabase mAppDatabase;


    @Inject
    public LoadCommonLocalVenueUseCase(LocalVenueRepository repository, Context context) {
        mRepository = repository;
        mContext = context;
        mAppDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "Sample.db")
                .build();
    }

    @Override
    public Single<List<LocalVenue>> execute() {
        return mRepository.getAll(mAppDatabase);
    }

    @Override
    public Single<Boolean> executeInsert(LocalVenue localVenue) {
        return mRepository.insert(mAppDatabase,localVenue);
    }

    @Override
    public Single<Boolean> executeDelete(LocalVenue localVenue) {
        return mRepository.delete(mAppDatabase,localVenue);
    }
}
