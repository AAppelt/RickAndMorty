package com.caramelheaven.rickandmorty.datasourse.db;

import com.caramelheaven.rickandmorty.datasourse.entity.location.Location;

import java.util.List;

import io.realm.Realm;

public class LocationDao {

    public LocationDao() {

    }

    public void addLocations(List<Location> locations) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(r -> r.insertOrUpdate(locations));
        }
    }
}
