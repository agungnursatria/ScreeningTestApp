package com.anb.screeningtestapp.Retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Agung Nursatria on 4/6/2018.
 */

public class RetroServer {

    public static final String BASE_URL = "http://dry-sierra-6832.herokuapp.com";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static RequestInterface getRequestService() {
        return getClient().create(RequestInterface.class);
    }
}
