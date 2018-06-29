package com.caramelheaven.rickandmorty.datasourse.entity.location;

import com.caramelheaven.rickandmorty.datasourse.entity.Info;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationRequest {
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<Location> results = null;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Location> getLocations() {
        return results;
    }

    public void setLocations(List<Location> results) {
        this.results = results;
    }
}
