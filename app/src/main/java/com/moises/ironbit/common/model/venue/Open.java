
package com.moises.ironbit.common.model.venue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Open {

    @SerializedName("renderedTime")
    @Expose
    private String renderedTime;

    public String getRenderedTime() {
        return renderedTime;
    }

    public void setRenderedTime(String renderedTime) {
        this.renderedTime = renderedTime;
    }

}
