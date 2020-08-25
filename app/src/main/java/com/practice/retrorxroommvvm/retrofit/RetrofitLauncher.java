package com.practice.retrorxroommvvm.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLauncher {

    static Retrofit retrofit;
    private static final String URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getInstance(){

        if (retrofit == null) {

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
