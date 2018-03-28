package com.anb.screeningtestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public class Guest {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("birthdate")
    @Expose
    public String birthday;

    public int image;

}
