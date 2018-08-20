package com.moises.ironbit.common.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mou on 19/08/18.
 */
@Entity(tableName = "venues")
public class LocalVenue {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "foursquareId")
    private String mFoursquareId;

    @ColumnInfo(name = "address")
    private String mAddress;

    public int getId() {
        return mId;
    }

    public String getFoursquareId() {
        return mFoursquareId;
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public LocalVenue(int id,String foursquareId, String name, String address) {
        this.mId=id;
        this.mFoursquareId = foursquareId;
        this.mName = name;
        this.mAddress = address;
    }

}
