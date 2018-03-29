package com.anb.screeningtestapp.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public class Event {

    public int image;
    public String nama;
    public String tgl;
    public ArrayList<String> hastag = new ArrayList<>();
    public String desc;
    public double laT;
    public double lonG;

    public Event(int image, String nama, String tgl, ArrayList<String> hastag, String desc, double laT, double lonG) {
        this.image = image;
        this.nama = nama;
        this.tgl = tgl;
        this.hastag = hastag;
        this.desc = desc;
        this.laT = laT;
        this.lonG = lonG;

    }
}
