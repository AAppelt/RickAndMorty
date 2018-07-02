package com.caramelheaven.rickandmorty.controllers.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;

import com.caramelheaven.rickandmorty.datasourse.db.CharacterDao;
import com.caramelheaven.rickandmorty.datasourse.db.LiveRealmResults;
import com.caramelheaven.rickandmorty.datasourse.entity.character.Character;
import com.caramelheaven.rickandmorty.datasourse.network.MainApiInterface;
import com.caramelheaven.rickandmorty.utils.AppUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import timber.log.Timber;

public class CharacterRepository {

    private Realm realm;
    private MainApiInterface apiInterface;
    private CharacterDao characterDao;
    private CompositeDisposable disposable;

    @Inject
    public CharacterRepository(MainApiInterface apiInterface, CharacterDao characterDao) {
        Timber.d("I'm inside CharacterRep!");
        this.apiInterface = apiInterface;
        this.characterDao = characterDao;
        realm = Realm.getDefaultInstance();
        disposable = new CompositeDisposable();
    }

    public LiveData<List<Character>> getCharacters() {
        Timber.d("Checking that this method call once!");
        //try to refresh data from network, if it available
        if (AppUtil.isNetworkConnectionAvailable()) {
            //in this we just load first page for our list
            loadCharacters(1);
        }
        return new LiveRealmResults<>(realm.where(Character.class).findAllAsync());
    }

    @SuppressLint("CheckResult")
    public void loadCharacters(int page) {
        Timber.d("I'm inside in loadCharacters!");
        disposable.add(apiInterface.getAllCharacters(page)
                .subscribeOn(Schedulers.io())
                .doOnNext(characterRequest -> {
                    if (characterRequest.getCharacters().size() != 0) {
                        List<Character> characters = characterRequest.getCharacters();
                        characterDao.addCharacters(characters);
                        for (Character character : characterRequest.getCharacters()) {
                            Timber.d("Load data: " + character.getName());
                        }
                    } else {
                        Timber.d("charactrs = null");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(characterRequest -> {
                    Timber.d("subscribe characterRequest: " + characterRequest.getCharacters().size());
                }, throwable -> {
                    Timber.d("Error 404 in CharacterRepository");
                }));
    }

    public void disposClear() {
        realm.close();
        disposable.clear();
    }

    public void loadMore(int page) {
        loadCharacters(page);
    }
}
