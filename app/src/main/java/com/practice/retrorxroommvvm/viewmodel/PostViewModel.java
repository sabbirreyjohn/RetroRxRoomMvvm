package com.practice.retrorxroommvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.practice.retrorxroommvvm.model.Post;
import com.practice.retrorxroommvvm.repository.PostRepository;
import com.practice.retrorxroommvvm.room.AppDataBase;
import com.practice.retrorxroommvvm.room.PostDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class PostViewModel extends AndroidViewModel {


    private AppDataBase appDataBase;
    private PostDao postDao;

    public PostViewModel(@NonNull Application application) {
        super(application);
        appDataBase = AppDataBase.getInstance(application);
        postDao = appDataBase.postDao();
    }

    public Single<Boolean> insertPost(Post post) {
        return PostRepository.getInstance().insertPost(postDao, post);
    }

    public LiveData<ArrayList<Post>> getPosts() {
        return PostRepository.getInstance().getAllPosts();
    }

    public Single<List<Post>> getLocalPosts() {
        return PostRepository.getInstance().getAllLocalPosts(postDao);
    }
}
