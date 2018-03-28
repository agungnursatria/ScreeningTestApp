package com.anb.screeningtestapp.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public interface RequestInterface {

    @GET("api/people")
    Call<ArrayList<Guest>> getJSON();
}
