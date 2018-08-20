package com.moises.ironbit.common.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mou on 19/08/18.
 */

@Database(entities = {LocalVenue.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocalVenueDao userDao();
}
