package com.moises.ironbit.common.domain.model;


import com.moises.ironbit.common.dao.AppDatabase;
import com.moises.ironbit.common.dao.LocalVenue;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class LocalVenueRepository {
    public Single<List<LocalVenue>> getAll(final AppDatabase appDatabase) {
        return Single.create(new SingleOnSubscribe<List<LocalVenue>>() {
            @Override
            public void subscribe(SingleEmitter<List<LocalVenue>> emitter) throws Exception {
                emitter.onSuccess(appDatabase.userDao().getAll());
            }
        });
    }

    public Single<Boolean> insert(final AppDatabase appDatabase,final LocalVenue localVenue) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                try {
                    appDatabase.userDao().insertAll(localVenue);
                    emitter.onSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public Single<Boolean> delete(final AppDatabase appDatabase,final LocalVenue localVenue) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                try {
                    appDatabase.userDao().delete(localVenue);
                    emitter.onSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
