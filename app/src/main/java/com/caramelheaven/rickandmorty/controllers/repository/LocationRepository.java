package com.caramelheaven.rickandmorty.controllers.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;

import com.caramelheaven.rickandmorty.datasourse.db.LiveRealmResults;
import com.caramelheaven.rickandmorty.datasourse.db.LocationDao;
import com.caramelheaven.rickandmorty.datasourse.entity.location.Location;
import com.caramelheaven.rickandmorty.datasourse.network.MainApiInterface;
import com.caramelheaven.rickandmorty.utils.AppUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import timber.log.Timber;

public class LocationRepository {

    private Realm realm;
    private MainApiInterface apiInterface;
    private LocationDao locationDao;
    private CompositeDisposable disposable;

    @Inject
    public LocationRepository(MainApiInterface apiInterface, LocationDao locationDao) {
        Timber.d("I;m inside LocationRepository");
        this.apiInterface = apiInterface;
        this.locationDao = locationDao;
        realm = Realm.getDefaultInstance();
        disposable = new CompositeDisposable();
    }

    public LiveData<List<Location>> getLocations() {
        Timber.d("Checking that this method call once!");
        if (AppUtil.isNetworkConnectionAvailable()) {
            loadLocations(1);
        }
        return new LiveRealmResults<>(realm.where(Location.class).findAllAsync());
    }

    @SuppressLint("CheckResult")
    public void loadLocations(int page) {
        disposable.add(apiInterface.getLocations(page)
                .subscribeOn(Schedulers.io())
                .doOnNext(locationRequest -> {
                    if (locationRequest.getLocations().size() != 0) {
                        List<Location> locations = locationRequest.getLocations();
                        locationDao.addLocations(locations);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locationRequest -> {
                    Timber.d("completed loading, size: " + locationRequest.getLocations().size());
                }, throwable -> {
                    Timber.d("Error 404 in LocationRepository");
                }));
    }

    public void disposClear() {
        realm.close();
        disposable.clear();
    }

    public void loadMore(int page) {
        loadLocations(page);
    }
}
