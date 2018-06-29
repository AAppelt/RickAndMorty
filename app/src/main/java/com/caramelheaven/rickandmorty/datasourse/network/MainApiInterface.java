package com.caramelheaven.rickandmorty.datasourse.network;

import com.caramelheaven.rickandmorty.datasourse.entity.character.CharacterRequest;
import com.caramelheaven.rickandmorty.datasourse.entity.location.LocationRequest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApiInterface {

    //get page characters
    @GET("character")
    Observable<CharacterRequest> getAllCharacters(@Query("page") int page);

    @GET("location")
    Observable<LocationRequest> getLocations(@Query("page") int page);
}
