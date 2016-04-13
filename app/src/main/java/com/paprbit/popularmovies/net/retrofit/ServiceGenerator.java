package com.paprbit.popularmovies.net.retrofit;

import android.content.Context;

import com.paprbit.popularmovies.net.retrofit.service.ApiService;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by ankush38u on 12/26/2015.
 */
public class ServiceGenerator {
    // creates ApiService instance as singleton
    private static ApiService service = null;
    private static Retrofit retrofit = null;


    public static ApiService getService() {
        //service to use without session
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        if (service == null) service = retrofit.create(ApiService.class);

        return service;
    }





}
