package com.caramelheaven.rickandmorty.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.caramelheaven.rickandmorty.datasourse.entity.character.Character;

import java.util.List;

public class CharacterDiffCallback extends DiffUtil.Callback {

    private final List<Character> characterOldList;
    private final List<Character> characterNewList;

    public CharacterDiffCallback(List<Character> characterOldList, List<Character> characterNewList) {
        this.characterOldList = characterOldList;
        this.characterNewList = characterNewList;
    }

    @Override
    public int getOldListSize() {
        return characterOldList.size();
    }

    @Override
    public int getNewListSize() {
        return characterNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return characterOldList.get(oldItemPosition).getId()
                .equals(characterNewList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Character oldCharacter = characterOldList.get(oldItemPosition);
        final Character newCharacter = characterNewList.get(newItemPosition);

        return oldCharacter.getName().equals(newCharacter.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //implementing this if we want to use animation of item
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
