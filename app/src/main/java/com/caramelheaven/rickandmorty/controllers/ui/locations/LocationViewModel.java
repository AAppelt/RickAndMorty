package com.caramelheaven.rickandmorty.controllers.ui.locations;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.caramelheaven.rickandmorty.RickAndMorty;
import com.caramelheaven.rickandmorty.controllers.repository.LocationRepository;
import com.caramelheaven.rickandmorty.datasourse.entity.location.Location;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class LocationViewModel extends ViewModel {

    @Inject
    LocationRepository repository;

    public LocationViewModel() {
        Timber.d("LocationViewModel created");
        RickAndMorty.getComponent().injectLocationViewModel(this);
    }

    public void loadMorePagination(int page) {
        repository.loadMore(page);
    }

    public LiveData<List<Location>> getLocations() {
        return repository.getLocations();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.disposClear();
    }
}
