package com.caramelheaven.rickandmorty.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.caramelheaven.rickandmorty.datasourse.entity.character.Character;
import com.caramelheaven.rickandmorty.datasourse.entity.location.Location;

import java.util.List;

//I can use generic, but I dont know how <_<. Still here just fun :)
public class LocationDiffCallback extends DiffUtil.Callback {

    private final List<Location> oldList;
    private final List<Location> newList;

    public LocationDiffCallback(List<Location> oldList, List<Location> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()
                .equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Location oldLocation = oldList.get(oldItemPosition);
        final Location newLocation = newList.get(newItemPosition);

        return oldLocation.getName().equals(newLocation.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //implementing this if we want to use animation of item
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
