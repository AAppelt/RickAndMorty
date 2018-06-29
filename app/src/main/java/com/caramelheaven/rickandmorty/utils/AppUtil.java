package com.caramelheaven.rickandmorty.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.caramelheaven.rickandmorty.RickAndMorty;

public final class AppUtil {

    public static boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) RickAndMorty.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}

