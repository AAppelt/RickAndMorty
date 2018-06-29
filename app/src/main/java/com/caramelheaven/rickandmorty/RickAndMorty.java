package com.caramelheaven.rickandmorty;

import android.app.Application;

import com.caramelheaven.rickandmorty.datasourse.DaggerRickAndMortyComponent;
import com.caramelheaven.rickandmorty.datasourse.RickAndMortyComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class RickAndMorty extends Application {

    private static RickAndMortyComponent component;
    private static RickAndMorty application;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        application = this;

        component = DaggerRickAndMortyComponent
                .builder()
                .build();

        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("rickMorty.db")
                .build());
    }

    public static RickAndMortyComponent getComponent() {
        return component;
    }

    public static RickAndMorty getApplication() {
        return application;
    }
}
