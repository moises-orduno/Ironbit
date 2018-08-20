package com.moises.ironbit.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mou on 17/08/18.
 */

public class Item {


    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("flags")
    @Expose
    private Flags flags;
    @SerializedName("referralId")
    @Expose
    private String referralId;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }


}
