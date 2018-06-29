package com.caramelheaven.rickandmorty.controllers.ui.characters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.caramelheaven.rickandmorty.RickAndMorty;
import com.caramelheaven.rickandmorty.controllers.repository.CharacterRepository;
import com.caramelheaven.rickandmorty.datasourse.entity.character.Character;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class CharacterViewModel extends ViewModel {

    @Inject
    CharacterRepository repository;

    public CharacterViewModel() {
        Timber.d("CharacterViewModel constructor created");
        RickAndMorty.getComponent().injectCharacterViewModel(this);
    }

    //load characters with support simple pagination
    public void loadMorePagination(int page) {
        repository.loadMore(page);
    }

    public LiveData<List<Character>> getCharacters() {
        return repository.getCharacters();
    }

    @Override
    protected void onCleared() {
        Timber.d("Deleting)");
        super.onCleared();
        repository.disposClear();
    }
}
