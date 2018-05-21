package com.anb.screeningtestapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Agung Nursatria on 5/21/2018.
 */

public class NetworkState {

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
