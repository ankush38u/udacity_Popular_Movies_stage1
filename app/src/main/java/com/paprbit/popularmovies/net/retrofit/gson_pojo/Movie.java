package com.paprbit.popularmovies.net.retrofit.gson_pojo;

import java.io.Serializable;

/**
 * Created by ankush38u on 4/13/2016.
 */
public class Movie implements Serializable {
    String poster_path;
    String overview;
    String original_title;
    String release_date;
    String title;
    float vote_average;

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }
}
