package com.caramelheaven.rickandmorty.datasourse;

import com.caramelheaven.rickandmorty.controllers.ui.characters.CharacterViewModel;
import com.caramelheaven.rickandmorty.controllers.ui.locations.LocationViewModel;
import com.caramelheaven.rickandmorty.datasourse.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface RickAndMortyComponent {

    void injectCharacterViewModel(CharacterViewModel characterViewModel);

    void injectLocationViewModel(LocationViewModel locationViewModel);
}
