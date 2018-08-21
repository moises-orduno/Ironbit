package com.moises.ironbit.common.model.venues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mou on 17/08/18.
 */

public class Flags {

    @SerializedName("outsideRadius")
    @Expose
    private Boolean outsideRadius;

    public Boolean getOutsideRadius() {
        return outsideRadius;
    }

    public void setOutsideRadius(Boolean outsideRadius) {
        this.outsideRadius = outsideRadius;
    }

}
