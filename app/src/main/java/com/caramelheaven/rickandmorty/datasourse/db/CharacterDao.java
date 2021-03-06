package com.caramelheaven.rickandmorty.datasourse.db;

import android.arch.lifecycle.LiveData;

import com.caramelheaven.rickandmorty.datasourse.entity.character.Character;

import java.util.List;

import io.realm.Realm;


public class CharacterDao {

    public CharacterDao() {

    }

    public void addCharacters(List<Character> characters) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(r -> r.insertOrUpdate(characters));
        }
    }

    public void deleteCharacter(Character character) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(r -> character.deleteFromRealm());
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
