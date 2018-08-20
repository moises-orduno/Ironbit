package com.moises.ironbit.common.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mou on 19/08/18.
 */

@Dao
public interface LocalVenueDao {

    @Query("SELECT * FROM venues")
    List<LocalVenue> getAll();

    @Insert
    void insertAll(LocalVenue... users);

    @Delete
    void delete(LocalVenue user);

}
