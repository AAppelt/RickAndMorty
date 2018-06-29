package com.caramelheaven.rickandmorty.datasourse.module;

import android.app.Application;

import com.caramelheaven.rickandmorty.datasourse.db.CharacterDao;
import com.caramelheaven.rickandmorty.datasourse.db.LocationDao;
import com.caramelheaven.rickandmorty.datasourse.network.MainApiInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    //network

    @Provides
    @Singleton
    public Retrofit getNetwork() {
        return new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public MainApiInterface getApiInterface(Retrofit retrofit) {
        return retrofit.create(MainApiInterface.class);
    }

    @Provides
    @Singleton
    public CharacterDao getCharacterDao() {
        return new CharacterDao();
    }

    @Provides
    @Singleton
    public LocationDao getLocationDao() {
        return new LocationDao();
    }

    @Provides
    @Singleton
    public Application provideApp() {
        return new Application();
    }
}
