package com.caramelheaven.rickandmorty.datasourse.entity.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;

public class Origin extends RealmObject {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Origin() {
    }

    public Origin(long id, String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Origin origin = (Origin) o;
        return Objects.equals(name, origin.name) &&
                Objects.equals(url, origin.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, url);
    }
}