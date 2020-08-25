package com.practice.retrorxroommvvm.retrofit;

import com.practice.retrorxroommvvm.model.Post;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PostHandler {

    @GET("/posts")
    Observable<ArrayList<Post>> getPosts();
}
